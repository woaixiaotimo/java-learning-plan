package com.ll.common.jms;

import com.alibaba.fastjson.JSONObject;

public interface MessageAdapter {

    void sendMsg(JSONObject body);

}
