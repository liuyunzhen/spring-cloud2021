package com.fox.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConfigApplication3366 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication3366.class, args);
    }
}
