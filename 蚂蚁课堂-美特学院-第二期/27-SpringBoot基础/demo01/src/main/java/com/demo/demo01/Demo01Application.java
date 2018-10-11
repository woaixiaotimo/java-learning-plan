package com.demo.demo01;

import com.demo.demo01.datasource.DBConfig1;
import com.demo.demo01.datasource.DBConfig2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @SpringBootApplication:只能扫描当前包下的类
 * */
@EnableCaching//开启缓存
@EnableConfigurationProperties({DBConfig1.class, DBConfig2.class})
@SpringBootApplication
public class Demo01Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo01Application.class, args);
	}
}
