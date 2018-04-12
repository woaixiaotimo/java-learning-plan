package demo.iocComplexDIXML;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCTest {

    public static void main(String[] args) {
        //1.加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("/iocComplexDIXML.xml");
        //2.得到配置创建的对象
        Bean bean = (Bean) context.getBean("bean");
        System.out.println("bean = " + bean);

        bean.method();

    }
}


