package com.dao;

import com.po.User;

import java.sql.SQLException;

/**
 * Created by 啊Q on 2018/4/17.
 */
public interface UserDao {
    public User findUserById(int id) throws SQLException;

    void insertUser(User user) throws SQLException;

    void deleteUserById(int id) throws SQLException;
}
