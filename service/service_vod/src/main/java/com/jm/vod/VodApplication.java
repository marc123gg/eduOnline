package com.jm.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 开启服务发现 nacos服务注册，服务发现，和自动配置
 */
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.jm"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class VodApplication {
    public static void main(String[] args){
        SpringApplication.run(VodApplication.class, args);
    }
}
