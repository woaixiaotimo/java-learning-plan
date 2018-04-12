package iocDemo.iocEasyDIXML;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCTest {

    public static void main(String[] args) {
        //1.加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("/iocEasyDIXML.xml");
        //2.得到配置创建的对象
        Bean bean = (Bean) context.getBean("bean");
        System.out.println("bean = " + bean);
        //构造注入展示
        bean.method();
        //set注入基础类型展示
        String name2 = bean.getName2();
        System.out.println("name2 = " + name2);
        //set注入对象类型
        Bean2 bean2 = bean.getBean2();
        bean2.method();
        //名称空间注入
        String setNameSpeace = bean.getNameSpeace();
        System.out.println("setNameSpeace = " + setNameSpeace);
    }
}


