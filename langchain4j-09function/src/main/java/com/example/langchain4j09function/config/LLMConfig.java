package com.example.langchain4j09function.config;

import com.example.langchain4j09function.service.FunctionAssistant;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.request.json.JsonObjectSchema;
import dev.langchain4j.model.chat.request.json.JsonStringSchema;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.tool.ToolExecutor;
import dev.langchain4j.service.tool.ToolProvider;
import dev.langchain4j.service.tool.ToolProviderRequest;
import dev.langchain4j.service.tool.ToolProviderResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration(proxyBeanMethods = false)
public class LLMConfig {

    @Bean
    public ChatLanguageModel chatLanguageModel() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("DASHSCOPE_KEY"))
                .modelName("qwen-max")
                .logRequests(true)
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public FunctionAssistant functionAssistant(ChatLanguageModel chatLanguageModel) {
        // 构建tool工具
        // 1、tool对象说明（描述）
//        ToolSpecification toolSpecification = ToolSpecification.builder()
//                .name("invoice_assistant")
//                .description("根据用户提交的开票信息，开具发票")
//                .addParameter("companyName", type("string"), description("公司名称"))
//                .addParameter("dutyNumber", type("string"), description("税号"))
//                .addParameter("amount", type("string"), description("金额，保留两位有效数字"))
//                .build();

        // (最新的API)1、tool对象说明（描述）
        ToolSpecification toolSpecification = ToolSpecification.builder()
                .name("invoice_assistant")
                .description("根据用户提交的开票信息，开具发票")
                .parameters(JsonObjectSchema.builder()
                        .addStringProperty("companyName", "公司名称")
                        .addStringProperty("dutyNumber", "税号")
                        .addProperty("amount", JsonStringSchema.builder().description("金额，保留两位有效数字").build())
                        .build())
                .build();

        // 业务逻辑 ToolExecutor
        ToolExecutor toolExecutor = (toolExecutionRequest, memoryId) -> {
            String arguments1 = toolExecutionRequest.arguments();
            System.out.println("执行开票 =>>>> " + arguments1);
            return "开具成功";
        };

        return AiServices.builder(FunctionAssistant.class)
                .chatLanguageModel(chatLanguageModel)
                // 构建tool工具（ToolSpecification-ToolExecutor对）
//                .tools(Map.of(toolSpecification, toolExecutor))
                // 另一种构建tool工具的方式
//                .tools(new InvoiceHandler())
                // 第三种构建tool工具方式
                .toolProvider(new ToolProvider() {
                    @Override
                    public ToolProviderResult provideTools(ToolProviderRequest request) {
                        log.info("provideTools =>>>> request.chatMemoryId:{}, request.userMessage:{}", request.chatMemoryId(), request.userMessage());

                        // 可以尝试将以下生成ToolProviderResult逻辑注释掉验证前后效果，效果就是提供了tool，那么大模型会尝试调用tool，没有tool，大模型就会以对话形式恢复一段对话
                        return ToolProviderResult.builder()
                                .add(toolSpecification, toolExecutor)
                                .build();

//                        return null;
                    }
                })
                .build();
    }

}
