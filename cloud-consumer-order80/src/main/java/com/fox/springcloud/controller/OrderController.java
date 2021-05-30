package com.fox.springcloud.controller;

import com.fox.springcloud.entity.CommonResult;
import com.fox.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    private static final String preffix_url = "http://PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/custmer/payment/save")
    public CommonResult<Payment> save(Payment payment){
        return restTemplate.postForObject(preffix_url + "/payment/save", payment, CommonResult.class);
    }

    @RequestMapping("/custmer/payment/get")
    public CommonResult<Payment> get(Long id){
        return restTemplate.getForObject(preffix_url + "/payment/get?id=" + id, CommonResult.class);
    }

    @RequestMapping("/custmer/payment/getEntity")
    public CommonResult<Payment> getEntity(Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(preffix_url + "/payment/get?id=" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        } else {
            return new CommonResult<>(500, "操作失败");
        }
    }
}
