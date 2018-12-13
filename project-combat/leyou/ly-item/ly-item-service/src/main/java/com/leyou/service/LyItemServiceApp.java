package com.leyou.service;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan("com.leyou.service.mapper")


@MapperScan("com.leyou.service.mapper")
@EnableEurekaClient
@SpringBootApplication
public class LyItemServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(LyItemServiceApp.class, args);
    }
}
