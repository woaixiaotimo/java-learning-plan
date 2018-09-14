package proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * JDK代理
 * Created by 啊Q on 2018/4/10.
 */
public class DynamicProxy {
    public static void main(String args[]) throws InterruptedException {
        RealSubject real = new RealSubject();
        Subject proxySubject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new ProxyHandler(real));

        proxySubject.doSomething();
    }
}
