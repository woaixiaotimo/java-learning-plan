package com.po;

import java.util.Date;

public class OrderCustom extends Orders {
//    添加用户的属性信息

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String username;// 用户姓名
    private String sex;// 性别
    private String address;// 地址
}
