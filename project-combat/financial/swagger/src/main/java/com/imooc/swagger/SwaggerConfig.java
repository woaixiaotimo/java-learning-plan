package com.imooc.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: what
 * @Date: 2018/12/24 17:27
 * @Description:
 */
@Configuration
@ComponentScan(basePackages = "com.imooc.swagger")
@EnableSwagger2
public class SwaggerConfig {


    @Autowired
    private SwaggerInfo info;

    /**
     * 如果需要更多的分组则写多个该方法
     */
    @Bean
    public Docket controllerApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName(info.getGroupName())
                .apiInfo(apiInfo());
        ApiSelectorBuilder builder = docket.select();

        if (!StringUtils.isEmpty(info.getBasePackage())) {

            builder = builder.apis(RequestHandlerSelectors.basePackage(info.getBasePackage()));
        }

        if (!StringUtils.isEmpty(info.getAntPath())) {
            builder = builder.paths(PathSelectors.ant(info.getAntPath()));
        }
        return builder.build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(info.getTitle())
                .description(info.getDescription())
                .termsOfServiceUrl("http://springfox.io")
                .contact("imooc")
                .license(info.getLicense())
                .licenseUrl("https://github.com/springfox/springfox/blob")
                .version("2.0")
                .build();

    }


}
