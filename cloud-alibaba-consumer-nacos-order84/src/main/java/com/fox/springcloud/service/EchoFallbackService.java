package com.fox.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class EchoFallbackService implements EchoService {
    @Override
    public String echo(Long id) {
        return "全局兜底业务异常处理";
    }
}
