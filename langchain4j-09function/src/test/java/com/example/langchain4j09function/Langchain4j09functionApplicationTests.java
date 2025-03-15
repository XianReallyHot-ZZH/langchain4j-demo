package com.example.langchain4j09function;

import com.example.langchain4j09function.service.FunctionAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Langchain4j09functionApplicationTests {


    @Autowired
    FunctionAssistant functionAssistant;


    @Test
    void contextLoads() {

        String chat = functionAssistant.chat("帮我开具一张发票， 公司：招银网络科技有限公司 税号：ZXC123 金额： 1000.12");

        System.out.println(chat);

    }

}
