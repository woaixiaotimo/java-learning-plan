package com.imooc.manager.controller;

import com.imooc.entity.ProductEntity;
import com.imooc.entity.enums.ProductStatus;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ProductController 功能测试用例
 *
 * @Auther: what
 * @Date: 2018/12/24 11:13
 * @Description:
 */
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//按照名称字典的顺序排序
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//使用随机端口
public class ProductControllerTest {

    private static RestTemplate restTemplate = new RestTemplate();


    @Value("http://localhost:${local.server.port}/manager")
    private String baseUrl;

    //正常的产品数据
    private static List<ProductEntity> normals = new ArrayList<>();

    //异常的产品数据
    private static List<ProductEntity> normalsE = new ArrayList<>();


    @BeforeClass//只执行一次
    public static void init() {


        ProductEntity p1 = new ProductEntity("T001", "灵活宝1号", BigDecimal.valueOf(1), BigDecimal.valueOf(5), 5, BigDecimal.valueOf(3.42), ProductStatus.AUDITING.name());
        ProductEntity p2 = new ProductEntity("T002", "灵活宝2号", BigDecimal.valueOf(1), BigDecimal.valueOf(5), 5, BigDecimal.valueOf(3.42), ProductStatus.AUDITING.name());
        ProductEntity p3 = new ProductEntity("T003", "灵活宝3号", BigDecimal.valueOf(1), BigDecimal.valueOf(5), 5, BigDecimal.valueOf(3.42), ProductStatus.AUDITING.name());
        ProductEntity p4 = new ProductEntity("T004", "灵活宝4号", BigDecimal.valueOf(1), BigDecimal.valueOf(5), 5, BigDecimal.valueOf(3.42), ProductStatus.AUDITING.name());

        normals.add(p1);
        normals.add(p2);
        normals.add(p3);
        normals.add(p4);


        ProductEntity pE1 = new ProductEntity(null, "灵活宝1号", BigDecimal.valueOf(1), BigDecimal.valueOf(5), 5, BigDecimal.valueOf(3.42), ProductStatus.AUDITING.name());

        normalsE.add(pE1);

        ResponseErrorHandler errorHandler = new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {

            }
        };
        restTemplate.setErrorHandler(errorHandler);

    }

    @Test
    public void create() {

        normals.forEach(productEntity -> {
            HashMap result = restTemplate.postForObject(baseUrl + "/product", productEntity, HashMap.class);
            Assert.notNull(result, "插入失败");

        });

    }

    @Test
    public void createException() {

        normalsE.forEach(productEntity -> {
            HashMap result = restTemplate.postForObject(baseUrl + "/product", productEntity, HashMap.class);
            Assert.notNull(result.get("code"), "插入成功");

        });
    }

    @Test
    public void findOne() {
        normals.forEach(productEntity -> {
            ProductEntity result = restTemplate.getForObject(baseUrl + "/product/" + productEntity.getId(), ProductEntity.class);
            Assert.isTrue(result.getId().equals(result.getId()), "查询失败");
        });

        normalsE.forEach(productEntity -> {
            ProductEntity result = restTemplate.getForObject(baseUrl + "/product/" + productEntity.getId(), ProductEntity.class);

            Assert.isTrue(result == null || result.getId() == null, "查询失败");
        });
    }

}
