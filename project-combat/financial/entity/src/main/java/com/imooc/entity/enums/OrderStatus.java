package com.imooc.entity.enums;

/**
 * 订单状态
 *
 * @Auther: 啊Q
 * @Date: 2018-12-22 16:08
 * @Description:
 */
public enum OrderStatus {


    INIT("初始化"),
    PROCESS("处理中"),
    SUCCESS("成功"),
    FAIL("失败");


    private String desc;

    OrderStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
