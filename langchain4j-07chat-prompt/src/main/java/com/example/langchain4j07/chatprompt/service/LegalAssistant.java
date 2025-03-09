package com.example.langchain4j07.chatprompt.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface LegalAssistant {

    /**
     * SystemMessage具有高优先级，能有效地指导模型的整体行为。例如，我们可以如下定义一个专业法律顾问的角色
     * UserMessage可以构建用户的提示词模板，规范用户的每一次交互输入，从而提高模型的对话质量。
     *
     * @param question
     * @return
     */
    @SystemMessage("你是一位专业的中国法律顾问，只回答与中国法律相关的问题。输出限制：对于其他领域的问题禁止回答，直接返回'抱歉，我只能回答中国法律相关的问题。'")
    @UserMessage("请回答以下法律问题：{{question}}")
    String chat(@V("question") String question);

    /**
     * StructuredPrompt基于结构化模板注解，对用户的输入进行模板填充，从而提高模型的对话质量。
     *
     * @param prompt
     * @return
     */
    @SystemMessage("你是一位专业的中国法律顾问，只回答与中国法律相关的问题。输出限制：对于其他领域的问题禁止回答，直接返回'抱歉，我只能回答中国法律相关的问题。'")
    String chat(LegalPrompt prompt);
}
