package com.jm.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jm"})
@MapperScan("com.jm.educenter.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class memberApplication {
    public static void main(String[] args){
        SpringApplication.run(memberApplication.class, args);
    }
}
