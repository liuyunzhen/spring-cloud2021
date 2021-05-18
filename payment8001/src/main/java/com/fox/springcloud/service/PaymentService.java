package com.fox.springcloud.service;

import com.fox.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    public int insert(Payment payment);

    public Payment selectPaymentById(Long id);
}
