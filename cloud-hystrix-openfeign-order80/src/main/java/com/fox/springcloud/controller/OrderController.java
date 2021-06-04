package com.fox.springcloud.controller;

import com.fox.springcloud.service.OrderService;
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

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo(@PathVariable("id") Long id){
        return orderService.paymentInfo(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable("id") Long id){
        return orderService.paymentInfo_timeout(id);
    }
}
