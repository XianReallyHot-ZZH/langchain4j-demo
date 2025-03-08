package com.example.langchain4j05.chatapi.vision;

import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Base64;

@SpringBootTest
class Langchain4j05chatapiVisionApplicationTests {

    @Autowired
    private ChatLanguageModel chatLanguageModel;

    @Value(("1.png"))
    private Resource resource;

    @Test
    void contextLoads() throws IOException {

        String encodeToString = Base64.getEncoder().encodeToString(resource.getContentAsByteArray());
        ChatResponse response = chatLanguageModel.chat(
                UserMessage.from(TextContent.from("从以下图片中获取 9.26号的上证指数"),
                        ImageContent.from(encodeToString, "image/png"))
        );
        System.out.println(response.aiMessage().text());
    }

}
