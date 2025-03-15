package com.example.langchain4j10dynamicfunction;

import com.example.langchain4j10dynamicfunction.service.AiAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Langchain4j10dynamicFunctionApplicationTests {

    @Autowired
    private AiAssistant aiAssistant;

    @Test
    void contextLoads() {

        String chat = aiAssistant.chat("What is the square root of 485906798473894056 in scientific notation?");
        System.out.println(chat);

    }

}
