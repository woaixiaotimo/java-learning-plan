package aopDemo.aopXml;

import aopDemo.aopXml.bean.Bean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 啊Q on 2018/4/13.
 */
public class Test {

    public static void main(String[] args) {
        //1.加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("/aopXml.xml");
        //2.得到配置创建的对象
        Bean bean = (Bean) context.getBean("bean");
        bean.test();
    }
}
