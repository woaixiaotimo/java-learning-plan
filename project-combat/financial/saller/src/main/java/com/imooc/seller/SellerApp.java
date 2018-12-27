package com.imooc.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


/**
 * 销售端启动类
 *
 * @Auther: 啊Q
 * @Date: 2018-12-22 16:16
 * @Description:
 */
@SpringBootApplication
public class SellerApp {

    public static void main(String[] args) {
        SpringApplication.run(SellerApp.class, args);
    }
}
