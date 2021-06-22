package com.fox.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaUrl;
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/payment/eurekaUrl")
    public String getEurekaUrl(){
        return eurekaUrl;
    }

    @GetMapping("/payment/configInfo")
    public String configInfo(){
        return configInfo;
    }
}
