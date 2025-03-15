package com.example.langchain4j13embeddingstore;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.grpc.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

@SpringBootTest
class Langchain4j13embeddingStoreApplicationTests {

    @Autowired
    EmbeddingModel embeddingModel;

    @Autowired
    private QdrantClient qdrantClient;

    @Autowired
    private EmbeddingStore<TextSegment> embeddingStore;

    @Test
    void embeddingModel() {

        // 利用大模型将文本进行向量化
        Response<Embedding> embeddingResponse = embeddingModel.embed("测试文本，文本向量化");

        System.out.println(embeddingResponse);
    }

    // 创建一个名为testv的集合，并设置其向量参数,类似于数据库中建一张表？
    @Test
    void createCollection() {
        Collections.VectorParams vectorParams = Collections.VectorParams.newBuilder()
                .setDistance(Collections.Distance.Cosine)
                .setSize(1024)
                .build();
        qdrantClient.createCollectionAsync("testv", vectorParams);
    }

    // 存储
    @Test
    void testText() {
        TextSegment segment1 = TextSegment.from("浏览器报错 404，请检测您输入的路径是否正确");
        segment1.metadata().put("author", "又又");
        Embedding embedding1 = embeddingModel.embed(segment1).content();
        // 向量存储
        embeddingStore.add(embedding1, segment1);
    }

    @Test
    void testQuery1(){
        Embedding queryEmbedding = embeddingModel.embed("404 是哪里的问题？").content();
        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(1)
                .build();
        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(embeddingSearchRequest);
        System.out.println(searchResult.matches().get(0).embedded().text());
    }

    @Test
    void testQuery2(){
        Embedding queryEmbedding = embeddingModel.embed("404 是哪里的问题？").content();
        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                // TextSegment文本的元数据中包含这个关键字才会返回
                .filter(metadataKey("author").isEqualTo("又又"))
                .maxResults(1)
                .build();

        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(embeddingSearchRequest);

        System.out.println(searchResult.matches().get(0).embedded().text());
    }


}
