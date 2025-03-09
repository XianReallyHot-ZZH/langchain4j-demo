package com.example.langchain4j06.chatmemory.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;

public interface AiAssistant {

    String chat(String message);

    /**
     * chat
     *
     * @param userId
     * @param message
     * @return
     */
    String chat(@MemoryId Long userId, @UserMessage String message);

}
