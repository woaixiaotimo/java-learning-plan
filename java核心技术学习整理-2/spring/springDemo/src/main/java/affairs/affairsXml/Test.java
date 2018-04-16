package affairs.affairsXml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("affairsXml.xml");
        Service service = (Service) ctx.getBean("service");
        service.testAffairs();
    }
}
