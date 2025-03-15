package com.example.langchain4j16rag2.config;

import com.example.langchain4j16rag2.service.ChatAssistant;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.jina.JinaScoringModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.scoring.ScoringModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.content.aggregator.ReRankingContentAggregator;
import dev.langchain4j.rag.content.injector.DefaultContentInjector;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.router.DefaultQueryRouter;
import dev.langchain4j.rag.query.transformer.CompressingQueryTransformer;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class LLMConfig {

    @Bean
    public ChatLanguageModel chatLanguageModel() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("DASHSCOPE_KEY"))
                .modelName("qwen-max")
                .logRequests(true)
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    /**
     * 嵌入存储 (简易内存存储)
     *
     * @return {@link InMemoryEmbeddingStore }<{@link TextSegment }>
     */
    @Bean
    public InMemoryEmbeddingStore<TextSegment> embeddingStore() {
        return new InMemoryEmbeddingStore<>();
    }

    @Bean
    public ChatAssistant assistant(ChatLanguageModel chatLanguageModel, EmbeddingStore<TextSegment> embeddingStore) {

        // 开源的一个评分大模型，用于重新排序检索到的内容。重排序模型的核心是计算用户查询与候选文档之间的语义匹配度，并根据该匹配度对结果进行重新排序。
        ScoringModel scoringModel = JinaScoringModel.builder()
                .apiKey(System.getenv("JINA_API_KEY"))
                .modelName("jina-reranker-v2-base-multilingual")
                .build();

        // 检索增强器，它通过从不同的数据源中检索相关内容来增强用户的消息。
        DefaultRetrievalAugmentor retrievalAugmentor = DefaultRetrievalAugmentor.builder()
                // 用于将原始查询转换为一个或多个新的查询，以提高检索的准确性
                .queryTransformer(new CompressingQueryTransformer(chatLanguageModel)) //  CompressingQueryTransformer 使用大语言模型（LLM）压缩查询和对话上下文，生成一个更简洁、独立的查询
                // 根据用户的查询从底层数据源中获取内容
                .contentRetriever(EmbeddingStoreContentRetriever.from(embeddingStore)) // EmbeddingStoreContentRetriever 使用嵌入模型将查询转化为向量，并从嵌入存储中检索相关的内容
                // 负责将查询分配到合适的内容检索器
                .queryRouter(new DefaultQueryRouter(EmbeddingStoreContentRetriever.from(embeddingStore))) // DefaultQueryRouter 将每个查询路由到所有配置的内容检索器。
                // 内容聚合器
                .contentAggregator(new ReRankingContentAggregator(scoringModel)) // ReRankingContentAggregator: 使用评分模型来重新排序检索到的内容
                // 内容注入器
                .contentInjector(new DefaultContentInjector()) // 注入内容
                .build();

        return AiServices.builder(ChatAssistant.class)
                .chatLanguageModel(chatLanguageModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .retrievalAugmentor(retrievalAugmentor)
                //.contentRetriever(EmbeddingStoreContentRetriever.from(embeddingStore))
                .build();
    }
}
