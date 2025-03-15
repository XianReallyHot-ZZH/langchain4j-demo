package com.example.langchain4j15rag1;

import com.example.langchain4j15rag1.service.ChatAssistant;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentByCharacterSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@SpringBootTest
class Langchain4j15rag1ApplicationTests {

    @Autowired
    InMemoryEmbeddingStore<TextSegment> embeddingStore;

    @Autowired
    ChatAssistant chatAssistant;

    @Test
    void contextLoads() {

        Document document = FileSystemDocumentLoader.loadDocument("D:\\Developer\\Document\\分布式技术资料\\技术选型\\分库分表技术选型-V1.docx");
        // 嵌入存储摄入器：读取文档（文档加载器），文档解析（文档解析器），文档转换（文档转换器），文档拆分（文档拆分器），向量化（向量大模型），向量存储
        EmbeddingStoreIngestor.ingest(document, embeddingStore);

        String chat = chatAssistant.chat("分库分表的选型建议是什么？");
        System.out.println(chat);

    }


    @Test
    void test1() throws FileNotFoundException {

        FileInputStream fileInputStream = new FileInputStream("D:\\Developer\\Document\\分布式技术资料\\技术选型\\分库分表技术选型-V1.docx");
        Document document = new TextDocumentParser().parse(fileInputStream);

        System.out.println(document);
    }

    @Test
    void test2() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("D:\\Developer\\Document\\分布式技术资料\\技术选型\\分库分表技术选型-V1.docx");
        Document document = new TextDocumentParser().parse(fileInputStream);

        List<TextSegment> split = new DocumentByCharacterSplitter(100, 0).split(document);

        System.out.println(split);

    }

}
