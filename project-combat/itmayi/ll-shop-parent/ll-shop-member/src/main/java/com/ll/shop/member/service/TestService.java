package com.ll.shop.member.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by 啊Q on 2018-12-09.
 */
public interface TestService {

    Map test(Integer id, String name);
}
