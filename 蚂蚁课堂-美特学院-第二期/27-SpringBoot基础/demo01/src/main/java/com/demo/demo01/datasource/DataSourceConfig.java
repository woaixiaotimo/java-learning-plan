package com.demo.demo01.datasource;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration//注册到spring容器中
@MapperScan(basePackages = "com.demo.demo01.entity.User", sqlSessionTemplateRef = "test1SqlSessionFactory")
public class DataSourceConfig {

    /**
     * @methodDesc: 功能描述:(配置test1数据库)
     * @author: 余胜军
     * @param: @return
     * @createTime:2017年9月17日 下午3:16:44
     * @returnType:@return DataSource
     * @copyright:上海每特教育科技有限公司
     * @QQ:644064779
     */
    @Bean(name = "test1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.test1")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }
}
