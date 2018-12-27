package com.imooc.manager.rpc;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.imooc.api.ProductRpc;
import com.imooc.api.domain.ProductRpcRequest;
import com.imooc.entity.ProductEntity;
import com.imooc.manager.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: what
 * @Date: 2018/12/27 11:39
 * @Description:
 */
@AutoJsonRpcServiceImpl
@Service
public class ProductRpcImpl implements ProductRpc {

    private static Logger log = LoggerFactory.getLogger(ProductRpcImpl.class);

    @Autowired
    private ProductService productService;

    @Override
    public List<ProductEntity> query(ProductRpcRequest req) {
        log.info("查询多个产品，请求:{}", req);

        Pageable pageable = PageRequest.of(0, 1000, Sort.Direction.DESC, "rewardRate");

        Page<ProductEntity> result = productService.quary(req.getIdLists(),
                req.getMinRewardRate(), req.getMaxRewardRate(),
                req.getStatusList(), pageable);

        log.info("查询多个产品，结果:{}", result);
        return result.getContent();
    }

    @Override
    public ProductEntity findOne(String id) {
        log.info("查询产品详情，请求:{}", id);

        ProductEntity result = productService.findOne(id);

        log.info("查询产品详情，结果:{}", result);
        return result;
    }
}
