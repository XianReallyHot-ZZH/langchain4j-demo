package com.example.langchain4j01.simple;

import dev.langchain4j.model.chat.ChatLanguageModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Langchain4j01simpleApplicationTests {

    @Autowired
    private ChatLanguageModel chatLanguageModel;


    @Test
    void contextLoads() {

        String response = chatLanguageModel.chat("你好,你是谁");
        System.out.println(response);

    }

}
