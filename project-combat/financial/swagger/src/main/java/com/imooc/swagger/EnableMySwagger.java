package com.imooc.swagger;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 * 开启swagger文档自动生成功能
 * @Auther: what
 * @Date: 2018/12/25 10:54
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(SwaggerConfig.class)
public @interface EnableMySwagger {
}
