package com.ll.shop.user.controllerAop;


import com.ll.common.beans.ResultBean;
import com.ll.common.exceptions.CheckException;
import com.ll.common.exceptions.UnloginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@ControllerAdvice
public class ControllerExceptionAop {


    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionAop.class);


    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResultBean<?> exceptionHandler(Throwable e) {
        ResultBean<?> result = new ResultBean();

        // 已知异常
        if (e instanceof CheckException) {
            result.setMsg(e.getLocalizedMessage());
            result.setCode(ResultBean.FAIL);
        }
        // 自己抛出的
        else if (e instanceof UnloginException) {
            result.setMsg("Unlogin");
            result.setCode(ResultBean.NO_LOGIN);

        } else if (e instanceof SQLException) {
            result.setMsg("数据库异常");
            result.setCode(ResultBean.FAIL);
        } else {
            //TODO 未知的异常，应该格外注意，可以发送邮件通知等

            logger.error("未知异常！", e);
            result.setMsg(e.toString());
            result.setCode(ResultBean.FAIL);
        }

        return result;
    }

}
