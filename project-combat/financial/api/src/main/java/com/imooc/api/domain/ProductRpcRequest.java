package com.imooc.api.domain;

import lombok.Data;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

/**
 * 产品相关rpc请求对象
 *
 * @Auther: what
 * @Date: 2018/12/27 11:31
 * @Description:
 */
@Data
public class ProductRpcRequest {

    private List<String> idLists;
    private BigDecimal minRewardRate;
    private BigDecimal maxRewardRate;
    private List<String> statusList;


    @Override
    public String toString() {
        return "ProductRpcRequest{" +
                "idLists=" + idLists +
                ", minRewardRate=" + minRewardRate +
                ", maxRewardRate=" + maxRewardRate +
                ", statusList=" + statusList +
                '}';
    }
}
