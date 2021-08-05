package com.fox.springcloud.controller;

import com.fox.springcloud.entity.CommonResult;
import com.fox.springcloud.entity.Payment;
import com.fox.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    private static final String preffix_url = "http://PAYMENT-SERVICE";

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;

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

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("PAYMENT-SERVICE");
        if (instances == null || instances.size() == 0){
            return null;
        } else {
            ServiceInstance instance = loadBalancer.instances(instances);
            URI uri = instance.getUri();
            return restTemplate.getForObject(uri + "/payment/lb", String.class);
        }
    }

    @RequestMapping("/custmer/payment/zipkin")
    public String paymentZipkin(){
        return restTemplate.getForObject(preffix_url + "/payment/zipkin", String.class);
    }
}
