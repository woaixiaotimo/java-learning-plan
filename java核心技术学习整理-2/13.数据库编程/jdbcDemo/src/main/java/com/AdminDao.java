package com;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * Created by 啊Q on 2018/4/1.
 */
public class AdminDao {


    static void test() {
        String sql = "select * from user";
        List<Map<String, String>> listMap = Dao.getInstance().queryForListMap(sql, null);
        System.out.println("listMap = " + listMap);

    }

    static void testInsert() {
        Connection connection = Dao.getInstance().beginTransaction();
        String sql = "INSERT INTO `user` ( `username`, `userpwd`) VALUES (?,?)";
        Object[] obj = {"lalla", "2"};
        int rows = Dao.getInstance().executeAddGetID(sql, obj);
        System.out.println("rows = " + rows);
//        test();
        Dao.getInstance().commit(connection);

    }

    static void test3() {
        String sql = "select * from user";
        Admin listMap = Dao.getInstance().queryForObject(sql, null,Admin.class);
//        for (int i = 0; i < listMap.size(); i++) {
            System.out.println(listMap.toString());
//        }
    }

    public static void main(String[] args) {
        test3();
    }


}
