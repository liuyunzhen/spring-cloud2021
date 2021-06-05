package com.fox.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    @HystrixCommand(fallbackMethod = "paymentInfo_timeout_handler", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable Long id){
        try {
            id = id / 0;
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = "线程:" + Thread.currentThread().getName() + " payment_timeout,id:" + id;
        log.info("******result:{}", result);
        return result;
    }

    public String paymentInfo_timeout_handler(Long id){
        return "线程:" + Thread.currentThread().getName() + " 系统繁忙请稍后再试,id:" + id + ",hystrix兜底处理";
    }
}
