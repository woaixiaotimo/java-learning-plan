package jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Dao {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
        String sql = "SELECT  * FROM  user";
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            //　重写processRow 来实现自己的循环处理结果集
            public void processRow(ResultSet rs) throws SQLException {
                int id = rs.getInt("id");
                System.out.println("id = " + id);
                String name = rs.getString("name");
                System.out.println("name = " + name);
            }
        });
    }
}
