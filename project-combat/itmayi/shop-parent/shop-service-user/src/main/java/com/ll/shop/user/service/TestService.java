package com.ll.shop.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ll.common.beans.PageRequest;
import com.ll.common.beans.PageResponse;
import com.ll.shop.user.entity.User;
import com.ll.shop.user.entity.UserExample;
import com.ll.shop.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private UserMapper userMapper;

    public PageResponse<User> findAllUser(PageRequest pageRequest) {

        PageHelper.startPage(pageRequest.getPage(), pageRequest.getPagesize());

        UserExample example = new UserExample();
        PageInfo<User> personPageInfo = new PageInfo<>(userMapper.selectByExample(example));

        return new PageResponse<User>(personPageInfo);
    }
}
