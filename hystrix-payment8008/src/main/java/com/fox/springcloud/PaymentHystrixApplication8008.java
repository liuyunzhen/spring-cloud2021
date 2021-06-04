package com.fox.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PaymentHystrixApplication8008 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixApplication8008.class, args);
    }
}
