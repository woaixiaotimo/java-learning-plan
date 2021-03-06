package com.ll.shop.msg;

import com.alibaba.fastjson.JSONObject;
import com.ll.common.constants.Constants;
import com.ll.common.jms.MessageAdapter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerDistribute {
    @Autowired
    private MailService mailService;

    private MessageAdapter messageAdapter;


    @JmsListener(destination = "messages_queue")
    public void distribute(String json) {
        log.info("####ConsumerDistribute###distribute() 消息服务平台接受 json参数:" + json);
        if (StringUtils.isEmpty(json)) {
            return;
        }
        JSONObject jsonObecjt = new JSONObject().parseObject(json);
        JSONObject header = jsonObecjt.getJSONObject("header");
        String interfaceType = header.getString("interfaceType");

        if (StringUtils.isEmpty(interfaceType)) {
            return;
        }
        if (interfaceType.equals(Constants.MSG_EMAIL)) {
            messageAdapter = mailService;
        }
        if (messageAdapter == null) {
            return;
        }
        JSONObject body = jsonObecjt.getJSONObject("content");
        messageAdapter.sendMsg(body);

    }

}

