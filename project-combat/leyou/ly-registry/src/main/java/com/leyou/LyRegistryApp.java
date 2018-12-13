package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 * 服务注册中心(eureka server )
 * @author 刘磊
 * @date 2018/12/13
 */
@SpringBootApplication
@EnableEurekaServer
public class LyRegistryApp {

    public static void main(String[] args) {
        SpringApplication.run(LyRegistryApp.class);
    }
}
