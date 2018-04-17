package com.dao;

import com.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * Created by 啊Q on 2018/4/17.
 */
public class Test {
    public static void main(String[] args) throws IOException, SQLException {

        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

        UserDaoImpl userDao = new UserDaoImpl(factory);
        User user = userDao.findUserById(1);
        System.out.println("user = " + user);
    }
}
