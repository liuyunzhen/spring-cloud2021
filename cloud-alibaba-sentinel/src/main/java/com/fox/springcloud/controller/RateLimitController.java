package com.fox.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fox.springcloud.entity.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "byResource_handler")
    public CommonResult byResource(){
        return new CommonResult(200, "按照资源名称限流");
    }

    public CommonResult byResource_handler(BlockException exception){
        return new CommonResult(500, exception.getLocalizedMessage() + "服务不可用");
    }
}
