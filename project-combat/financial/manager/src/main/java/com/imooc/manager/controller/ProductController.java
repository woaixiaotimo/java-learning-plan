package com.imooc.manager.controller;

import com.imooc.entity.ProductEntity;
import com.imooc.manager.service.ProductService;
import org.apache.catalina.util.ToStringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 产品
 *
 * @Auther: 啊Q
 * @Date: 2018-12-23 09:53
 * @Description:
 */
@RestController
@RequestMapping("/product")
public class ProductController {


    private Logger log = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ProductEntity addProduct(@RequestBody ProductEntity productEntity) {
        log.info("创建产品,参数{}", productEntity);

        ProductEntity result = productService.addProduct(productEntity);

        log.info("创建产品,结果{}", result);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProductEntity findOne(@PathVariable String id) {
        log.info("查询单个产品，id={}", id);

        ProductEntity result = productService.findOne(id);

        log.info("查询单个产品，结果={}", id);
        return result;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<ProductEntity> query(String ids,
                                     BigDecimal minRewardRate, BigDecimal maxRewardRate,
                                     String status,
                                     @RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        log.info("查询产品，ids={}，minRewardRate={}，maxRewardRate={}，status={}，pageNum={}，pageSize={}");
        List<String> idList = null;
        List<String> statusList = null;

        if (!StringUtils.isEmpty(ids)) {
            idList = Arrays.asList(ids.split(","));
        }

        if (!StringUtils.isEmpty(ids)) {
            statusList = Arrays.asList(status.split(","));
        }
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        Page<ProductEntity> pageResult = productService.quary(idList, minRewardRate, maxRewardRate, statusList, pageRequest);

        log.info("查询结果，pageResult={}", pageResult);

        return pageResult;
    }
}
