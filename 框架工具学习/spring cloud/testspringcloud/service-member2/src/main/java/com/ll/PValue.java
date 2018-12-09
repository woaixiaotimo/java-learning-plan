package com.ll;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PValue {


    @Value("${server.port}")
    private String name;
}
