package com.fox.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient("PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping("/payment/lb")
    public String getPaymentLB();
}
