package com.mapper;

import com.po.OrderCustom;
import com.po.Orders;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class OrderTest {
    public static void main(String[] args) throws IOException, SQLException {
        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = factory.openSession();

        OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);
        List<OrderCustom> list = mapper.findOrdersUser();
        System.out.println("list = " + list);


        List<Orders> list2 = mapper.findOrdersUserResultMap();
        System.out.println("list2 = " + list2);
        List<Orders> list3 = mapper.findOrdersAndOrderDetailResultMap();
        System.out.println("list3 = " + list3);
    }
}
