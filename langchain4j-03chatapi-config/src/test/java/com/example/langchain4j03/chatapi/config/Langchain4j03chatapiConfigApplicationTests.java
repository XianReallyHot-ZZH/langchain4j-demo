package com.example.langchain4j03.chatapi.config;

import com.example.langchain4j03.chatapi.config.service.ChatAssistant;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.output.Response;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Langchain4j03chatapiConfigApplicationTests {

    @Autowired
    private ChatLanguageModel chatLanguageModel;

    @Test
    void contextLoads() {
        String generate = chatLanguageModel.chat("你好");

        System.out.println(generate);
    }

    @Test
    void testChatMessage(){
        UserMessage userMessage = UserMessage.from("你好");
        ChatResponse generate = chatLanguageModel.chat(userMessage);

        System.out.println(generate);
    }

    @Resource
    private ChatAssistant chatAssistant;

    @Test
    void chat() {

        String chat = chatAssistant.chat("hello, 杭州什么好吃的？");

        System.out.println(chat);
    }

}
