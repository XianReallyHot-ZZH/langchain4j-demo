package com.example.langchain4j03.chatapi.config.service;

public interface ChatAssistant {

    /**
     * 聊天
     *
     * @param message 消息
     * @return {@link String }
     */
    String chat(String message);

}
