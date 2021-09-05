package com.fox.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SentinelApplication8401 {
    public static void main(String[] args) {
        SpringApplication.run(SentinelApplication8401.class, args);
    }
}
