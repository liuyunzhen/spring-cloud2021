package com.fox.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpenfeignOrderApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(OpenfeignOrderApplication80.class, args);
    }
}
