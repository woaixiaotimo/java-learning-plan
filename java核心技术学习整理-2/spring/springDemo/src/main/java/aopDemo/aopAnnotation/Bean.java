package aopDemo.aopAnnotation;

import org.springframework.stereotype.Component;


@Component("bean")
public class Bean {
    public void test() {
        System.out.println("aop test");
    }
}
