package com.imooc.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @Auther: 啊Q
 * @Date: 2018-12-22 16:16
 * @Description:
 */
@EntityScan("com.imooc.entity")
@SpringBootApplication
public class ManagerApp {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApp.class, args);
    }
}
