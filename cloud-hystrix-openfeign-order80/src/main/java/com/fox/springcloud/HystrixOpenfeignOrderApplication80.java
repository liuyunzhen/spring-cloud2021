package com.fox.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class HystrixOpenfeignOrderApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixOpenfeignOrderApplication80.class, args);
    }
}
