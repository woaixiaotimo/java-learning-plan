package affairs.affairsAnnotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("affairsAnnotation.xml");
        Service service = (Service) ctx.getBean("service");
        service.testAffairs();
    }
}
