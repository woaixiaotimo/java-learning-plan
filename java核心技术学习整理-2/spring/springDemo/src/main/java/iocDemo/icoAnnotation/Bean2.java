package iocDemo.icoAnnotation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by 啊Q on 2018/4/12.
 */
@Component("bean2")
@Scope()
public class Bean2 {

    public void method() {
        System.out.println("Bean2 - method");
    }
}
