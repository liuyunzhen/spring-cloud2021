package com.fox.springcloud.service;

import com.fox.springcloud.factory.EchoFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(value = "nacos-ribbon-provider", fallback = EchoFallbackService.class)
@FeignClient(value = "nacos-ribbon-provider", fallbackFactory = EchoFallbackFactory.class)
public interface EchoService {

    @GetMapping(value = "/echo/{id}")
    public String echo(@PathVariable("id") Long id);
}
