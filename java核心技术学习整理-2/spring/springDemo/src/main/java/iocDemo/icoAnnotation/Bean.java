package iocDemo.icoAnnotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//设置为组件,并设置名称
@Component("bean")
@Scope
public class Bean {
    public void method() {
        System.out.println("Bean - method");
    }

    public Bean2 getBean2() {
        return bean2;
    }

    public void setBean2(Bean2 bean2) {
        this.bean2 = bean2;
    }

    @Autowired
    private Bean2 bean2;

    public Bean2 getBean3() {
        return bean3;
    }

    @Resource(name = "bean2")
    private Bean2 bean3;

    public String getA() {
        return a;
    }

    @Value("A")
    private String a;
}
