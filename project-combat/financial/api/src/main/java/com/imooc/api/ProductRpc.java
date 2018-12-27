package com.imooc.api;

import com.googlecode.jsonrpc4j.JsonRpcService;
import com.imooc.api.domain.ProductRpcRequest;
import com.imooc.entity.ProductEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 产品相关的rpc服务
 *
 * @Auther: what
 * @Date: 2018/12/27 11:26
 * @Description:
 */
@JsonRpcService("rpc/product")
public interface ProductRpc {

    /**
     * 查询多个产品并分页
     */
    List<ProductEntity> query(ProductRpcRequest productRpcRequest);


    /**
     * 查询单个产品
     */
    ProductEntity findOne(String id);

}
