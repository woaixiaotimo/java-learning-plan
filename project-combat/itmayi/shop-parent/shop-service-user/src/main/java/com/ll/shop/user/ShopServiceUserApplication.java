package com.ll.shop.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@MapperScan("com.ll.shop.user.mapper")//将项目中对应的mapper类的路径加进来就可以了
@SpringBootApplication
public class ShopServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopServiceUserApplication.class, args);
    }
}
