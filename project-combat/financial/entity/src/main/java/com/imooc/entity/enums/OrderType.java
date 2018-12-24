package com.imooc.entity.enums;

/**
 * 订单类型
 *
 * @Auther: 啊Q
 * @Date: 2018-12-22 16:04
 * @Description:
 */
public enum OrderType {

    APPLY("申购"),
    REDEEM("赎回");


    private String desc;

    OrderType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
