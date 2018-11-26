package com.example.demo.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//这个类用配置redis服务器的连接

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)//maxInactiveIntervalInSeconds为SpringSession的过期时间（单位：秒）
public class SessionConfig {

//    //冒号后的值为没有配置文件时，制动装载的默认值
//    @Value("${spring.redis.host:localhost}")
//    String HostName;
//    @Value("${spring.redis.port:6379}")
//    int Port;
//
//    @Bean
//    public JedisConnectionFactory connectionFactory() {
//        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(HostName, Port);
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(configuration);
//        return jedisConnectionFactory;
//    }
}

