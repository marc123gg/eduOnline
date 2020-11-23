package com.jm.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import javax.swing.*;

/**
 * 阿里云oss储存模块
 * @author marc
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.jm"})
@EnableDiscoveryClient
public class OssApplication {
    public static void main(String[] args){
        SpringApplication.run(OssApplication.class, args);
    }
}
