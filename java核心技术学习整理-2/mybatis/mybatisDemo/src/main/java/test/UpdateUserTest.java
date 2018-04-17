package test;

import com.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class UpdateUserTest {
    public static void main(String[] args) throws IOException {

        //mybatis配置文件
        String resource = "SqlMapConfig.xml";
        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //创建会话工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

        //通过工厂得到session
        SqlSession sqlSession = factory.openSession();

        //插入用户对象
        User user = new User();
        user.setId(31);
        user.setUsername("啦啦啦");
        user.setBirthday(new Date());
        user.setSex("1");
        user.setAddress("河南");
        int id = sqlSession.update("test.updateUSer", user);

        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }
}
