package com.imooc.seller.config;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcClientProxyCreator;
import com.imooc.api.ProductRpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * rpc相关配置
 *
 * @Auther: what
 * @Date: 2018/12/27 13:07
 * @Description:
 */
@Configuration
@ComponentScan(basePackageClasses = {ProductRpc.class})
public class RpcConfig {

    private Logger log = LoggerFactory.getLogger(RpcConfig.class);

    @Bean
    public AutoJsonRpcClientProxyCreator rpcClientProxyCreator(@Value("${rpc.manager.url}") String url) {
        AutoJsonRpcClientProxyCreator creator = new AutoJsonRpcClientProxyCreator();

        try {
            creator.setBaseUrl(new URL(url));
        } catch (MalformedURLException e) {
            log.error("创建RPC服务地址错误");
        }
        creator.setScanPackage(ProductRpc.class.getPackage().getName());
        return creator;

    }
}
