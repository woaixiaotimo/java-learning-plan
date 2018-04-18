package com.mapper;

import com.po.OrderCustom;
import com.po.Orderdetail;
import com.po.Orders;
import com.po.User;

import java.sql.SQLException;
import java.util.List;

public interface OrdersMapperCustom {

    //查询订单关联查询用户信息
    public List<OrderCustom> findOrdersUser()throws Exception;

    //查询订单关联查询用户使用resultMap
    public List<Orders> findOrdersUserResultMap()throws Exception;
    //查询订单(关联用户)及订单明细
    public List<Orders>  findOrdersAndOrderDetailResultMap()throws Exception;

    //查询用户购买商品信息
    public List<User>  findUserAndItemsResultMap()throws Exception;

    //查询订单关联查询用户，用户信息是延迟加载
    public List<Orders> findOrdersUserLazyLoading()throws Exception;
}
