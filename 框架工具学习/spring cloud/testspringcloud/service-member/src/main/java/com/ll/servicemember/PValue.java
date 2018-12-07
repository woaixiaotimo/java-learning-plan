package com.ll.servicemember;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PValue {


    @Value("${server.port}")
    private String name;
}
