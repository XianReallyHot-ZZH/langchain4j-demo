package com.example.langchain4j08.chatoutput.service;

import dev.langchain4j.service.UserMessage;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 测试不同的原生数据类型输出
 */
public interface NumberExtractor {

    // text=我赚了100块
    @UserMessage("Extract a number from {{it}}")
    int extractInt(String text);

    // text=我赚了100块
    @UserMessage("Extract a long number from {{it}}")
    long extractLong(String text);

    // text=我赚了100块
    @UserMessage("Extract a big integer from {{it}}")
    BigInteger extractBigInteger(String text);

    // text=我赚了100块
    @UserMessage("Extract a float number from {{it}}")
    float extractFloat(String text);

    // text=我赚了100块
    @UserMessage("Extract a double number from {{it}}")
    double extractDouble(String text);

    // text=我赚了100块
    @UserMessage("Extract a big decimal from {{it}}")
    BigDecimal extractBigDecimal(String text);

}
