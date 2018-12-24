package com.imooc.manager.error;

import lombok.Getter;

/**
 * 错误种类
 *
 * @Auther: what
 * @Date: 2018/12/24 10:47
 * @Description:
 */
@Getter
public enum ErrorEnum {

    ID_NOT_NULL("F001", "编号不可为空", false),
    UNKNOW("999", "未知异常", false);



    private String code;
    private String message;
    private Boolean canRetry;

    ErrorEnum(String code, String message, Boolean canRetry) {
        this.code = code;
        this.message = message;
        this.canRetry = canRetry;
    }

    public static ErrorEnum getByCode(String code) {
        for (ErrorEnum errorEnum : ErrorEnum.values()) {
            if (errorEnum.code.equals(code)) {
                return errorEnum;
            }
        }
        return UNKNOW;
    }

}
