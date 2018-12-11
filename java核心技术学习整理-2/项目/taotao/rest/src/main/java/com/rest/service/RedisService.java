package com.rest.service;

import com.common.pojo.TaotaoResult;

public interface RedisService {

    TaotaoResult syncContent(long contentCid);
}
