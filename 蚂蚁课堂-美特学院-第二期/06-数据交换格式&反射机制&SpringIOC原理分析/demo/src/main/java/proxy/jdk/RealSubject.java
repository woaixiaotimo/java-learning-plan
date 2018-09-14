package proxy.jdk;

/**
 * Created by 啊Q on 2018/4/10.
 */
public class RealSubject implements Subject {
    public void doSomething() {
        System.out.println( "call doSomething()" );
    }
}
