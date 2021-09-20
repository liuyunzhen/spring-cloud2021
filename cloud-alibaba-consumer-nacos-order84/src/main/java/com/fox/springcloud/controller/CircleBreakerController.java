package com.fox.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fox.springcloud.entity.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class CircleBreakerController {

    private static final String service_url = "http://nacos-ribbon-provider";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consume/echo/{id}")
    @SentinelResource(value = "fallback", fallback = "handler_fallback", blockHandler = "handler_block", exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<String> callback(@PathVariable Long id){
        if (id == 4){
            throw new IllegalArgumentException("IllegalArgumentException 非法参数异常。。。");
        }
        return new CommonResult<>(200, restTemplate.getForObject(service_url + "/echo/" + id, String.class));
    }

    public CommonResult<String> handler_fallback(@PathVariable Long id, Throwable e){
        return new CommonResult<>(500, "全局兜底业务异常处理：" + e.getMessage());
    }

    public CommonResult<String> handler_block(@PathVariable Long id, BlockException e){
        return new CommonResult<>(543, "全局兜底流控规则异常处理：" + e.getClass().getName());
    }
}
