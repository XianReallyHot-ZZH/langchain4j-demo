package com.example.langchain4j14embeddingclassifier;

import dev.langchain4j.classification.EmbeddingModelTextClassifier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Langchain4j14embeddingClassifierApplicationTests {

    @Autowired
    private EmbeddingModelTextClassifier<PersonalityTrait> textClassifier;

    @Test
    void textClassifier() {
//        List<PersonalityTrait> personalityTraitList= textClassifier.classify("赠人玫瑰，手有余香");
        List<PersonalityTrait> personalityTraitList= textClassifier.classify("我喜欢解决复杂的问题");

        System.out.println(personalityTraitList);
    }

}
