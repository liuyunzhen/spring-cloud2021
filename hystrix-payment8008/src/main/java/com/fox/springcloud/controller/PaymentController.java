package com.fox.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo(@PathVariable Long id){
        String result = "线程:" + Thread.currentThread().getName() + " payment_ok,id:" + id;
        log.info("******result:{}", result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable Long id){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = "线程:" + Thread.currentThread().getName() + " payment_timeout,id:" + id;
        log.info("******result:{}", result);
        return result;
    }
}
