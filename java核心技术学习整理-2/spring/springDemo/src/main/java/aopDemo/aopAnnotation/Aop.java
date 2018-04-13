package aopDemo.aopAnnotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by 啊Q on 2018/4/13.
 */
@Component
@Aspect
public class Aop {

    @Before("execution(* aopDemo.aopAnnotation.Bean.*(..))")
    public void before() {
        System.out.println("前置增强");
    }

    @After("execution(* aopDemo.aopAnnotation.Bean.*(..))")
    public void after() {
        System.out.println("后置增强");
    }

//    @Around("execution(* aopDemo.aopAnnotation.Bean.test(..))")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("环绕之前");
        //执行被增强的方法
        Object returnObj = point.proceed();
        System.out.println("环绕之后");
    }
}
