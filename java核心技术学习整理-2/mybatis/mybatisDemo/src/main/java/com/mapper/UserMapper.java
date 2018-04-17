package com.mapper;

import com.po.UserQueryVo;
import com.po.User;
import com.po.UserCustom;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 啊Q on 2018/4/17.
 */
public interface UserMapper {


    List<UserCustom> findUserList(UserQueryVo userQueryVo) throws SQLException;

    int findUserCount(UserQueryVo userQueryVo) throws SQLException;

    User findUserById(int id) throws SQLException;

    void insertUser(User user) throws SQLException;

    void deleteUserById(int id) throws SQLException;
}
