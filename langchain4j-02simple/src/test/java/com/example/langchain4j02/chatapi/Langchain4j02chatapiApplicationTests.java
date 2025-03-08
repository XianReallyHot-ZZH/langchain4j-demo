package com.example.langchain4j02.chatapi;

import com.example.langchain4j02.chatapi.service.ChatAssistant;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Langchain4j02chatapiApplicationTests {

    @Autowired
    private ChatAssistant chatAssistant;

    @Test
    void chat() {
        String chat = chatAssistant.chat("你好，我的名字叫小帅");
        System.out.println(chat);
        String chat2 = chatAssistant.chat("我的名字是什么？");
        System.out.println(chat2);
    }

    @Autowired
    private ChatLanguageModel chatLanguageModel;

    @Test
    void contextLoads() {
        String generate = chatLanguageModel.generate("你好");
        System.out.println(generate);
    }

    @Test
    void testChatMessage() {
        UserMessage userMessage = UserMessage.from("你好");
        Response<AiMessage> generate = chatLanguageModel.generate(userMessage);

        System.out.println(generate);
    }


}
