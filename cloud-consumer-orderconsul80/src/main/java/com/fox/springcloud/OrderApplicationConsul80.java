package com.fox.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderApplicationConsul80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplicationConsul80.class, args);
    }
}
