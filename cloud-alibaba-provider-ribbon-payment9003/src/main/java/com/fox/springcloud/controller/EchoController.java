package com.fox.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/echo/{id}")
    public String echo(@PathVariable Long id) {
        if (id == 4){
            throw new IllegalArgumentException("IllegalArgumentException 非法参数异常。。。");
        }
        return "Hello Nacos Discovery " + serverPort + ",id:" + id;
    }
}