package com.example.langchain4j04.chatstream.controller;

import com.example.langchain4j04.chatstream.service.ChatAssistant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private ChatAssistant chatAssistant;

    /**
     * 在浏览器测试流式输出
     * 案例：http://localhost:8080/demo/chat?message=杭州有什么好吃的？
     *
     * @param message
     * @return
     */
    @GetMapping("/chat")
    public Flux<String> chat(@RequestParam("message") String message) {
        return chatAssistant.chat(message);
    }

}
