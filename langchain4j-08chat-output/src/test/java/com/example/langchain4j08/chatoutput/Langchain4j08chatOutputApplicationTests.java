package com.example.langchain4j08.chatoutput;

import com.example.langchain4j08.chatoutput.service.NumberExtractor;
import com.example.langchain4j08.chatoutput.service.PersonExtractor;
import com.example.langchain4j08.chatoutput.service.SentimentAnalyzer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Langchain4j08chatOutputApplicationTests {

    @Autowired
    private NumberExtractor numberExtractor;

    @Test
    void numberExtractor() {
        int days = numberExtractor.extractInt("我今天要请五天假");

        System.out.println(days);
    }

    @Autowired
    private PersonExtractor personExtractor;

    @Test
    void extractPerson() {

        PersonExtractor.Person person = personExtractor.extractPerson("帮我创建一个用户，名字叫张三，生日是1990-01-01");

        System.out.println(person);
    }

    @Autowired
    private SentimentAnalyzer sentimentAnalyzer;

    @Test
    void isPositive() {
        boolean positive = sentimentAnalyzer.isPositive("假期结束开始上班");
        System.out.println(positive);
    }

    @Test
    void analyzeSentimentOf() {
        SentimentAnalyzer.Sentiment sentiment = sentimentAnalyzer.analyzeSentimentOf("假期结束开始上班");

        System.out.println(sentiment);
    }

}
