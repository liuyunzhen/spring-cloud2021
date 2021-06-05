package com.fox.springcloud.service.impl;

import com.fox.springcloud.service.OrderService;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    @Override
    public String paymentInfo(Long id) {
        return "paymentIfo fall back 在服务上配置服务降级";
    }

    @Override
    public String paymentInfo_timeout(Long id) {
        return "paymentIfo time out fall back 在服务上配置服务降级";
    }
}
