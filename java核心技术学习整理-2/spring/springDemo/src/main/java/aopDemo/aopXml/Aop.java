package aopDemo.aopXml;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by 啊Q on 2018/4/13.
 */
public class Aop {

    public void before() {
        System.out.println("前置增强");
    }

    public void after() {
        System.out.println("后置增强");
    }

    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("环绕之前");
        //执行被增强的方法
        Object returnObj = point.proceed();
        System.out.println("环绕之后");
    }
}
