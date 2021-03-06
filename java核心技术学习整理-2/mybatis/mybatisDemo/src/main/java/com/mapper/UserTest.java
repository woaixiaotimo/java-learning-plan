package com.mapper;

import com.po.User;
import com.po.UserCustom;
import com.po.UserQueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 啊Q on 2018/4/17.
 */
public class UserTest {
    public static void main(String[] args) throws IOException, SQLException {

        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);


        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findUserById(1);
        System.out.println("user = " + user);


        UserQueryVo queryVo = new UserQueryVo();
        UserCustom custom = new UserCustom();
        custom.setSex("1");
        custom.setUsername("张");
        queryVo.setUserCustom(custom);
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(24);
        queryVo.setIds(ids);


        UserMapper mapper1 = sqlSession.getMapper(UserMapper.class);
        List<UserCustom> userCustomList = mapper1.findUserList(queryVo);
        System.out.println("userCustomList = " + userCustomList);
        int count = mapper.findUserCount(queryVo);
        System.out.println("count = " + count);

        User user1 = mapper.findUserByIdMap(1);
        System.out.println("user1 = " + user1);

        System.exit(0);
    }
}
