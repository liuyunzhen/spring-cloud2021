package com.fox.springcloud.controller;

import com.fox.springcloud.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @HystrixCommand(fallbackMethod = "paymentInfo_timeout_fallback", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "15000")
    })
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo(@PathVariable("id") Long id){
        return orderService.paymentInfo(id);
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_timeout_fallback", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "9000")
    })
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id") Long id){
        return orderService.paymentInfo_timeout(id);
    }

    public String paymentInfo_timeout_fallback(@PathVariable("id") Long id){
        return "我是订单模块80，支付模块业务繁忙请稍后再试" + id;
    }
}
