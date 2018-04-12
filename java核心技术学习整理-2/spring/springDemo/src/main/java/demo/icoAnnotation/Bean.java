package demo.icoAnnotation;

import org.springframework.stereotype.Component;

//设置为组件,并设置名称
@Component(value = "bean")
public class Bean {
    public void method() {
        System.out.println("method");
    }
}
