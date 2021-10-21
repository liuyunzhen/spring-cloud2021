package com.fox.springcloud.factory;

import com.fox.springcloud.service.EchoService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class EchoFallbackFactory implements FallbackFactory<EchoService> {
    @Override
    public EchoService create(Throwable cause) {
        return id -> "EchoFallbackFactory兜底熔断处理" + cause.getMessage();
//        return new EchoService() {
//            @Override
//            public String echo(Long id) {
//                return "EchoFallbackFactory兜底熔断处理" + cause.getMessage();
//            }
//        };
    }
}
