package com.jm.canal;

import com.jm.canal.client.canalClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class canalApplication implements CommandLineRunner {

    @Resource
    private canalClient canalClient;

    public static void main(String[] args) {
        SpringApplication.run(canalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        canalClient.run();
    }
}
