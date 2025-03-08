package com.example.langchain4j02.chatapi.service;

/**
 * 聊天助手
 */
public interface ChatAssistant {

    /**
     * 聊天
     *
     * @param question
     * @return
     */
    String chat(String question);

}
