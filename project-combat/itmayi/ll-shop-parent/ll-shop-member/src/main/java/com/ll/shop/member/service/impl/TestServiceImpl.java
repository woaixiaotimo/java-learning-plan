package com.ll.shop.member.service.impl;

import com.ll.shop.member.service.TestService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 啊Q on 2018-12-09.
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public Map test(Integer id, String name) {
        Map map = new HashMap();
        map.put(id, name);
        return map;
    }
}
