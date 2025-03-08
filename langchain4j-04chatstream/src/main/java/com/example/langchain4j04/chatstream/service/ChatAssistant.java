package com.example.langchain4j04.chatstream.service;

import reactor.core.publisher.Flux;

public interface ChatAssistant {

    /**
     * 聊天
     *
     * @param message 消息
     * @return {@link Flux }<{@link String }>
     */
    Flux<String> chat(String message);

}
