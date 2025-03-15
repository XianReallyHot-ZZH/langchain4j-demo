package com.example.langchain4j11websearch;

import com.example.langchain4j11websearch.service.ChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Langchain4j11webSearchApplicationTests {

    @Autowired
    private ChatAssistant chatAssistant;

    @Test
    void chatAssistant() {
        String chat = chatAssistant.chat("找一下最近一个月（2025年三月份）在github上关于AI的热点项目，总结10个项目出来吧");
//        String chat = chatAssistant.chat("总结一下今天国内的热点新闻吧");

        System.out.println(chat);
    }

}
