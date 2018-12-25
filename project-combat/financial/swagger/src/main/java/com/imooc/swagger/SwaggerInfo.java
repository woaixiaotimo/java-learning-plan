package com.imooc.swagger;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: what
 * @Date: 2018/12/25 10:34
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerInfo {

    private String groupName = "controller";

    private String basePackage;

    private String antPath;

    private String title = "HTTP API";

    private String description = "管理端接口";

    private String license = "Apache License Version 2.0";
}
