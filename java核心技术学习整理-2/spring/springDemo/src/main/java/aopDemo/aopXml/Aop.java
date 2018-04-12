package aopDemo.aopXml;

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

    public void around() {
        System.out.println("环绕增强");
    }
}
