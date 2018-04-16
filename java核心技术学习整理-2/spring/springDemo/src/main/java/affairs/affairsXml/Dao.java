package affairs.affairsXml;

import org.springframework.jdbc.core.JdbcTemplate;

public class Dao {

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private JdbcTemplate jdbcTemplate;

    public void update() {
        String sql = "UPDATE `user` SET `name`=? WHERE id = ?;";
        int updateRows = jdbcTemplate.update(sql, "李四", "1");
        System.out.println("修改id = 1 ，name = 张三 的用户记录");
        if (updateRows > 0) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }

    public void update2() {
        String sql = "UPDATE `user` SET `name`=? WHERE id = ?;";
        int updateRows = jdbcTemplate.update(sql, "王五", "1");
        System.out.println("修改id = 1 ，name = 张三 的用户记录");
        if (updateRows > 0) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }
}
