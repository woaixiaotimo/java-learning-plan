package jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class Dao {


    public void add(JdbcTemplate jdbcTemplate) {
        String sql = "INSERT INTO `user` (`id`, `name`) VALUES (?,?);";
        int updateRows = jdbcTemplate.update(sql, "1", "张三");
        System.out.println("插入id = 1 ，name = 张三 的用户记录");
        if (updateRows > 0) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }

    }

    public void select(JdbcTemplate jdbcTemplate) {
        RowMapper<User> rowmapper =
                new RowMapper<User>() {
                    public User mapRow(
                            ResultSet rs,
                            int index)
                            throws SQLException {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");

                        return new User(id, name);
                    }
                };


        String sql = "SELECT * FROM user where id = ?";
        User user = jdbcTemplate.queryForObject(sql, rowmapper, 1);
        System.out.println("使用queryForObject方法查询，结果为 " + user.toString());

        Map map = jdbcTemplate.queryForMap(sql, 1);
        System.out.println("使用queryForMap方法查询，结果为 " + map);

        List<User> userList = jdbcTemplate.query(sql, rowmapper, 1);
        System.out.println("使用query方法查询，结果为 " + userList);
    }

    public void update(JdbcTemplate jdbcTemplate) {
        String sql = "UPDATE `user` SET `name`=? WHERE id = ?;";
        int updateRows = jdbcTemplate.update(sql, "李四", "1");
        System.out.println("修改id = 1 ，name = 张三 的用户记录");
        if (updateRows > 0) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }

    public void delete(JdbcTemplate jdbcTemplate) {
        String sql = "DELETE FROM `user`WHERE id = ?";
        int updateRows = jdbcTemplate.update(sql, "1");
        System.out.println("删除id = 1 ，name = 李四 的用户记录");
        if (updateRows > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }


    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");

        Dao dao = new Dao();
        dao.add(jdbcTemplate);
        dao.select(jdbcTemplate);
        dao.update(jdbcTemplate);
        dao.delete(jdbcTemplate);
    }

    class User {
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private int id;
        private String name;

        @Override
        public String toString() {
            return "id = " + id + ", name" + name;
        }
    }
}
