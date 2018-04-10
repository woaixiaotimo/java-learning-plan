package com;

import java.lang.reflect.Proxy;

/**
 * Created by 啊Q on 2018/4/10.
 */
public class DynamicProxy {
    public static void main( String args[] ) throws InterruptedException {
        RealSubject real = new RealSubject();
        Subject proxySubject = (Subject )Proxy.newProxyInstance(Subject.class.getClassLoader(),
                new Class[]{Subject.class},
                new ProxyHandler(real));

        proxySubject.doSomething();

        /*Test1 test1 = new Test1();
        Test1 proxySubject1 = (Test1)Proxy.newProxyInstance(Test1.class.getClassLoader(),
             new Class[]{Test1.class},
             new ProxyHandler(test1));

        proxySubject1.doSome();*/
        /*createProxyClassFile(); */
    }
}
