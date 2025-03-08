package com.example.langchain4j04.chatstream.config;

import com.example.langchain4j04.chatstream.service.ChatAssistant;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

@Configuration(proxyBeanMethods = false)
public class LLMConfig {

    @Bean
    public ChatLanguageModel chatLanguageModel() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("DASHSCOPE_KEY"))
                .modelName("qwen-max")
                .logRequests(true)
                .logResponses(true)
                .maxRetries(1)
                .timeout(Duration.ofSeconds(30))
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public StreamingChatLanguageModel streamingChatLanguageModel() {
        return OpenAiStreamingChatModel.builder()
                .apiKey(System.getenv("DASHSCOPE_KEY"))
                .modelName("qwen-max")
                .logRequests(true)
                .logResponses(true)
                .timeout(Duration.ofSeconds(30))
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
     }

     @Bean
     public ChatAssistant chatAssistant(StreamingChatLanguageModel streamingChatLanguageModel) {
         return AiServices.create(ChatAssistant.class, streamingChatLanguageModel);
     }


}
