package com.fox.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fox.springcloud.entity.CommonResult;
import com.fox.springcloud.handler.GlobalBlockExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    @SentinelResource(value = "testA", blockHandlerClass = GlobalBlockExceptionHandler.class, blockHandler = "testA_handler")
    public CommonResult testA(){
        log.info(Thread.currentThread().getName());
        return new CommonResult(200, Thread.currentThread().getName());
    }

    @GetMapping("/testB")
    @SentinelResource(value = "bb", blockHandler = "deal_testB")
    public String testB(){
        return "---------------testB";
    }

    public String deal_testB(BlockException blockException){
        return "---------------testB limit" + blockException.fillInStackTrace();
    }

    @GetMapping("/testC")
    @SentinelResource(value = "testC", blockHandlerClass = GlobalBlockExceptionHandler.class, blockHandler = "testC_handler")
    public CommonResult testC(){
        return new CommonResult(200, "testC");
    }

    @GetMapping("/testD")
    public String testD() throws InterruptedException {
        Thread.sleep(500);
        return "---------------testD";
    }

    @GetMapping("/testParamKey")
    @SentinelResource(value = "aa", blockHandler = "deal_testParamKey")
    public String testParamKey(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "age", required = false) String age){
        System.out.println("name:" + name + "，age:" + age);
        return "---------------testParamKey";
    }

    public String deal_testParamKey(String name, String age, BlockException blockException){
        return "---------------deal_testParamKey";
    }
}
