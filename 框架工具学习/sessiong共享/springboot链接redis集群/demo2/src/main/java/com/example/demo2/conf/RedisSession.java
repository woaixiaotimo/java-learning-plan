package com.example.demo2.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//这个类用配置redis服务器的连接

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)//maxInactiveIntervalInSeconds为SpringSession的过期时间（单位：秒）
public class RedisSession {
    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }

}

