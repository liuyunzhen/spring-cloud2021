package com.fox.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosPaymentApplication9003 {
    public static void main(String[] args) {
        SpringApplication.run(NacosPaymentApplication9003.class, args);
    }
}
