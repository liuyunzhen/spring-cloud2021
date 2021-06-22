package com.fox.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RefreshScopeTestController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/payment/configInfo2")
    public String configInfo(){
        return configInfo;
    }
}
