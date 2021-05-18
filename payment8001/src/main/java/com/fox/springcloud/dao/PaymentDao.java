package com.fox.springcloud.dao;

import com.fox.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    public int insert(Payment payment);

    public Payment selectPaymentById(@Param("id") Long id);
}
