package com.example.langchain4j10dynamicfunction;

import dev.langchain4j.code.CodeExecutionEngine;
import dev.langchain4j.code.graalvm.GraalVmJavaScriptExecutionEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GraalVMTests {

    @Test
    public void test() {

//        // 设置系统属性以避免潜在问题
//        System.setProperty("polyglot.engine.WarnInterpreterOnly", "false");
//        System.setProperty("polyglot.engine.AllowExperimentalOptions", "true");

        CodeExecutionEngine engine = new GraalVmJavaScriptExecutionEngine();
        System.out.println("Engine initialized successfully: " + engine);

        String code = """
        function fibonacci(n) {
            if (n <= 1) return n;
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
                        
        fibonacci(10)
        """;

        try {
            // 执行代码并打印结果
            String result = engine.execute(code);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
