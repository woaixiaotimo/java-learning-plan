package com.ll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceMember2Application {

	public static void main(String[] args) {
		SpringApplication.run(ServiceMember2Application.class, args);
	}
}
