package com.mapper;

import com.po.OrderCustom;
import com.po.Orderdetail;
import com.po.Orders;

import java.sql.SQLException;
import java.util.List;

public interface OrdersMapperCustom {

    List<OrderCustom> findOrdersUser() throws SQLException;
    List<Orders> findOrdersUserResultMap() throws SQLException;
    List<Orders> findOrdersAndOrderDetailResultMap() throws SQLException;
}
