package com.jm.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jm"})
@MapperScan("com.jm.educms.mapper")
public class CmsApplication {
    public static void main(String[] args){
        SpringApplication.run(CmsApplication.class, args);
    }
}
