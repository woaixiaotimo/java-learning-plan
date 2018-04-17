package com.dao;

import com.po.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;

/**
 * Created by 啊Q on 2018/4/17.
 */
public class UserDaoImpl implements UserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    public User findUserById(int id) throws SQLException {
        SqlSession sqlSession = factory.openSession();

        User user = sqlSession.selectOne("test.findUserById", id);
        sqlSession.close();
        return user;
    }

    public void insertUser(User user) throws SQLException {
        SqlSession sqlSession = factory.openSession();

        sqlSession.insert("test.insertUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    public void deleteUserById(int id) throws SQLException {
        SqlSession sqlSession = factory.openSession();
        sqlSession.delete("test.deleteUser", id);
        sqlSession.commit();
        sqlSession.close();
    }
}
