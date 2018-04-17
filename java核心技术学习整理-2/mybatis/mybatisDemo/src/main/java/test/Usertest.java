package test;

import com.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Usertest {

    public static void main(String[] args) throws IOException {

        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

        //通过工厂得到session
        SqlSession sqlSession = factory.openSession();

        User user = sqlSession.selectOne("test.findUserById", 1);
        System.out.println("user = " + user);

        List<User> userList = sqlSession.selectList("test.findUserByName", "%小%");
        System.out.println("userList = " + userList);

        sqlSession.close();
    }
}
