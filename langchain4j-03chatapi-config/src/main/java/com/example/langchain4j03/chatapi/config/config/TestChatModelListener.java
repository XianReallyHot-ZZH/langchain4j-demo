package com.example.langchain4j03.chatapi.config.config;

import dev.langchain4j.model.chat.listener.ChatModelErrorContext;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelRequestContext;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestChatModelListener implements ChatModelListener {

    @Override
    public void onRequest(ChatModelRequestContext requestContext) {
        // 增加请求参数，这个attributes什么用途可以查看一下源码，好像是为了存储一些自定义的参数，用于多个listener之间传递数据
        requestContext.attributes().put("test", "test");
        log.info("请求参数:{}", requestContext);
    }

    @Override
    public void onResponse(ChatModelResponseContext responseContext) {
        Object object = responseContext.attributes().get("test");
        log.info("返回结果:{}", object);
    }

    @Override
    public void onError(ChatModelErrorContext errorContext) {
        log.error("请求异常:{}", errorContext);
    }
}
