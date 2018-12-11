package com.ll.shop.user.service;

import com.alibaba.fastjson.JSONObject;
import com.ll.common.aop.ControllerAOP;
import com.ll.common.constants.Constants;
import com.ll.common.utils.MD5Util;
import com.ll.shop.user.entity.User;
import com.ll.shop.user.mapper.UserMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(ControllerAOP.class);

    @Autowired
    private UserMapper userMapper;

    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public int addUser(User user) throws Exception {

        user.setCreated(new Date());
        user.setUpdated(new Date());
        // 参数验证
        String password = user.getPassword();
        if (StringUtils.isEmpty(password)) {

            throw new Exception("密码不能为空.");
        }
        String newPassword = MD5Util.MD5(password);

        // 采用异步方式发送消息
        String email = user.getEmail();
        String json = emailJson(email);
        log.info("####会员服务推送消息到消息服务平台####json:{}", json);
//        sendMsg(json);

        return userMapper.insertSelective(user);
    }

    private String emailJson(String email) {
        JSONObject rootJson = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("interfaceType", Constants.MSG_EMAIL);
        JSONObject content = new JSONObject();
        content.put("email", email);
        rootJson.put("header", header);
        rootJson.put("content", content);
        return rootJson.toJSONString();
    }

//    private void sendMsg(String json) {
//        ActiveMQQueue activeMQQueue = new ActiveMQQueue(MESSAGESQUEUE);
//        registerMailboxProducer.sendMsg(activeMQQueue, json);
//
//    }
}
