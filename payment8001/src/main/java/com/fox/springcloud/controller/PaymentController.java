package com.fox.springcloud.controller;

import com.fox.springcloud.entity.CommonResult;
import com.fox.springcloud.entity.Payment;
import com.fox.springcloud.service.PaymentService;
import com.fox.springcloud.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RefreshScope
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String port;

    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaUrl;

    @Value("${config.info}")
    private String configInfo;

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

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("****service:{}", service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return discoveryClient;
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

    @GetMapping("/payment/eurekaUrl")
    public String getEurekaUrl(){
        return eurekaUrl;
    }

    @GetMapping("/payment/configInfo")
    public String configInfo(){
        return configInfo;
    }
}
