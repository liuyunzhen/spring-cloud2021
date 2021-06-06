package com.fox.springcloud.controller;

import cn.hutool.core.util.IdUtil;
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
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "15000")
    })
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_timeout(@PathVariable Long id){
        try {
            Thread.sleep(16000);
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

    @HystrixCommand(fallbackMethod = "paymentCircutHandler", commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled", value = "true"),  //是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "10"),  //请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value = "10000"),  //时间窗口期
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value = "60"),   //失败率达到多少后跳闸
    })
    @GetMapping("/payment/hystrix/circut/{id}")
    public String paymentCircutBreaker(@PathVariable Long id){
        if (id < 0){
            throw new RuntimeException("id不合法");
        }
        String result = "线程:" + Thread.currentThread().getName() + " 调用成功,流水号:" + IdUtil.simpleUUID();
        log.info("******result:{}", result);
        return result;
    }

    public String paymentCircutHandler(Long id){
        return "id不合法，请稍后再试";
    }
}
