package com.imooc.seller.service;

import com.imooc.api.ProductRpc;
import com.imooc.api.domain.ProductRpcRequest;
import com.imooc.entity.ProductEntity;
import com.imooc.entity.enums.ProductStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品服务
 *
 * @Auther: what
 * @Date: 2018/12/27 12:53
 * @Description:
 */
@Service
public class ProductService {

    private Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRpc productRpc;

    public List<ProductEntity> findAll() {
        ProductRpcRequest request = new ProductRpcRequest();
        List<String> status = new ArrayList<>();
        status.add(ProductStatus.AUDITING.name());


        request.setStatusList(status);

        log.info("rpc查询全部产品,请求:{}", request);

        List<ProductEntity> result = productRpc.query(request);

        log.info("rpc查询全部产品,结果:{}", result);
        return result;
    }

    public ProductEntity findOne() {
        String id = "001";
        log.info("rpc查询单个产品,请求:{}", id);
        ProductEntity result = productRpc.findOne(id);
        log.info("rpc查询单个产品,结果:{}", result);
        return result;
    }

    @PostConstruct
    public void testFindAll() {
        findAll();
    }

    @PostConstruct
    public void testFindOne() {
        findOne();
    }

}
