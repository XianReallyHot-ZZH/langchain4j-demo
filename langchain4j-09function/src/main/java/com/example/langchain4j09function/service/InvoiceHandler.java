package com.example.langchain4j09function.service;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvoiceHandler {

    /**
     * 利用注解构建tool工具
     *
     * @param companyName
     * @param dutyNumber
     * @param amount
     * @return
     */
    @Tool("根据用户提交的开票信息进行开票")
    public String handle(@P("公司名称") String companyName,@P("税号") String dutyNumber,@P("金额保留两位有效数字") String amount) {
        log.info("开票执行中~~~ companyName =>>>> {} dutyNumber =>>>> {} amount =>>>> {}", companyName, dutyNumber, amount);
        return "开票成功";
    }



}
