package com.fox.springcloud.service.impl;

import com.fox.springcloud.dao.PaymentDao;
import com.fox.springcloud.entity.Payment;
import com.fox.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int insert(Payment payment) {
        return paymentDao.insert(payment);
    }

    @Override
    public Payment selectPaymentById(Long id) {
        return paymentDao.selectPaymentById(id);
    }
}
