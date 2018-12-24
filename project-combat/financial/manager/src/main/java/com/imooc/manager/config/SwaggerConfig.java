package com.imooc.manager.config;

import com.imooc.manager.controller.ProductController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: what
 * @Date: 2018/12/24 17:27
 * @Description:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 如果需要更多的分组则写多个该方法
     */
    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("controller")
                .apiInfo(apiInfo())
                .select().apis(RequestHandlerSelectors.basePackage(ProductController.class.getPackage().getName()))//选择包路径
//                .paths(PathSelectors.ant("/product/*"))//ant的匹配规则，可以精确的显示接口显示
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("HTTP API")
                .description("管理端接口")
                .termsOfServiceUrl("http://springfox.io")
                .contact("imooc")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob")
                .version("2.0")
                .build();

    }


}
