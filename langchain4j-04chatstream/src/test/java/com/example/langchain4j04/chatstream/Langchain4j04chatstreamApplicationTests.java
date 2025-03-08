package com.example.langchain4j04.chatstream;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Langchain4j04chatstreamApplicationTests {

    @Autowired
    private StreamingChatLanguageModel streamingChatLanguageModel;

    @Test
    void streamingChatLanguageModel() throws InterruptedException {

//        streamingChatLanguageModel.generate("hello, 杭州有什么好吃的？", new StreamingResponseHandler<AiMessage>() {
//            @Override
//            public void onNext(String token) {
//                System.out.println(token);
//            }
//
//            @Override
//            public void onError(Throwable error) {
//
//            }
//        });


        streamingChatLanguageModel.chat("hello, 杭州有什么好吃的？", new StreamingChatResponseHandler() {
            @Override
            public void onPartialResponse(String partialResponse) {
                System.out.println(partialResponse);
            }

            @Override
            public void onCompleteResponse(ChatResponse completeResponse) {
                System.out.println(completeResponse);
            }

            @Override
            public void onError(Throwable error) {
                System.out.println(error.toString());
            }
        });


        Thread.sleep(20000);
    }

}
