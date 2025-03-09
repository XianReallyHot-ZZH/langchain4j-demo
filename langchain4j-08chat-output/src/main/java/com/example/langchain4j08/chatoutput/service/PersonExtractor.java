package com.example.langchain4j08.chatoutput.service;

import dev.langchain4j.model.output.structured.Description;
import dev.langchain4j.service.UserMessage;
import lombok.Data;

import java.time.LocalDate;

/**
 * 测试java pojo对象输出
 */
public interface PersonExtractor {

    @UserMessage("Extract information about a person from {{it}}")
    Person extractPerson(String text);

    @Data
    class Person {
        @Description("name of a person") // 增加字段描述，让大模型更理解字段含义
        private String name;
        private LocalDate birthDate;
    }

}
