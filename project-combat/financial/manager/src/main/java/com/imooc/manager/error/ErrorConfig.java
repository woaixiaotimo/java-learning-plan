package com.imooc.manager.error;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 错误处理相关配置
 *
 * @Auther: what
 * @Date: 2018/12/24 10:41
 * @Description:
 */
@Configuration
public class ErrorConfig {


    @Bean
    public MyErrorController basicErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
        return new MyErrorController(errorAttributes, serverProperties.getError());
    }

}
