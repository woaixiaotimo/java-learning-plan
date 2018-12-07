package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Value {

    @org.springframework.beans.factory.annotation.Value()
    private String name;
}
