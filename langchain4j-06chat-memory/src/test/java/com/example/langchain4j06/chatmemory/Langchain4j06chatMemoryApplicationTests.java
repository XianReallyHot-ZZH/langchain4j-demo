package com.example.langchain4j06.chatmemory;

import com.example.langchain4j06.chatmemory.service.AiAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Langchain4j06chatMemoryApplicationTests {

    @Autowired
    private AiAssistant aiAssistant;

    @Test
    void aiAssistant() {

        String chat = aiAssistant.chat("你好 我的名字是小帅");

        System.out.println(chat);


        chat = aiAssistant.chat("你好 我的名字是什么？");

        System.out.println(chat);
    }

    @Test
    void contextLoads() {

        aiAssistant.chat(1L, "你好！我的名字是小帅.");
        aiAssistant.chat(2L, "你好！我的名字是小龙.");

        String chat = aiAssistant.chat(1L, "我的名字是什么");
        System.out.println(chat);

        chat = aiAssistant.chat(2L, "我的名字是什么");
        System.out.println(chat);

    }

}
