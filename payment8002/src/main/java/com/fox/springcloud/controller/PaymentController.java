package com.fox.springcloud.controller;

import com.fox.springcloud.entity.CommonResult;
import com.fox.springcloud.entity.Payment;
import com.fox.springcloud.service.PaymentService;
import com.fox.springcloud.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @PostMapping("/payment/save")
    public CommonResult save(@RequestBody Payment payment){
        log.info("===================port:{}", port);
        return ResultUtil.success(paymentService.insert(payment));
    }

    @GetMapping("/payment/get")
    public CommonResult selectPaymentById(Long id){
        log.info("===================port:{}", port);
        return ResultUtil.success(paymentService.selectPaymentById(id));
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return port;
    }

    @GetMapping("/payment/timeout")
    public String getTimeout(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            log.error("线程休眠出错:{}", e);
        }
        return port;
    }
}
