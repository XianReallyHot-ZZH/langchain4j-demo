package com.example.langchain4j07.chatprompt.service;

import dev.langchain4j.model.input.structured.StructuredPrompt;
import lombok.Data;

/**
 * 提示工程（利用StructuredPrompt注解）
 */
@Data
@StructuredPrompt("根据中国{{legal}}法律，解答以下问题：{{question}}")
public class LegalPrompt {

    private String legal;
    private String question;

}
