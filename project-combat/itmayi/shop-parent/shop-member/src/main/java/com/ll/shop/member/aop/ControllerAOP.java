package com.ll.shop.member.aop;

import com.ll.shop.common.beans.ResultBean;
import com.ll.shop.common.exception.CheckException;
import com.ll.shop.common.exception.UnloginException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * 处理和包装异常
 *
 * @author 肖文杰
 */
@Aspect
@Component
@Order(-99)
public class ControllerAOP {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);

    @Pointcut("execution(public com.ll.shop.member.controller. **(..))")
    public void controllerMethod() {
    }

    @Around("controllerMethod()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        ResultBean<?> result;

        try {
            result = (ResultBean<?>) pjp.proceed();
            logger.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }

        return result;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> result = new ResultBean();

        // 已知异常
        if (e instanceof CheckException) {
            result.setMsg(e.getLocalizedMessage());
            result.setErrNo(ResultBean.FAIL);
        }
        // 自己抛出的
        else if (e instanceof UnloginException) {
            result.setMsg("Unlogin");
            result.setErrNo(ResultBean.NO_LOGIN);
        } else {
            logger.error(pjp.getSignature() + " error ", e);

            //TODO 未知的异常，应该格外注意，可以发送邮件通知等
            result.setMsg(e.toString());
            result.setErrNo(ResultBean.FAIL);
        }

        return result;
    }
}
