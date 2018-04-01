package com;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.beanutils.BeanUtils;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Created by 啊Q on 2018/3/31.
 */
public class Dao {

    //当前类的单例对象
    private static Dao instance = null;

    //配置文件路径
    private static String configFilePath = "/db.properties";
    // 驱动包名和数据库url
    private static String url = "jdbc:mysql://localhost:3306/test?allowMultiQueries=true&useUnicode=true" +
            "&characterEncoding=gb2312";
    private static String driverClass = "com.mysql.jdbc.Driver";
    // 数据库用户名和密码
    private static String userName = "root";
    private static String password = "";
    //数据库连接池对象
    private ComboPooledDataSource dataSource = null;

    /**
     * 初始化驱动程序
     * 静态代码块中（只加载一次）
     */
    static {
        InputStream in = null;
        try {
            //读取db.properties文件
            Properties prop = new Properties();

            /**
             * 使用类路径的读取方式
             *  / : 斜杠表示classpath的根目录
             *     在java项目下，classpath的根目录从bin目录开始
             *     在web项目下，classpath的根目录从WEB-INF/classes目录开始
             */
            in = Dao.class.getResourceAsStream(configFilePath);

            //加载文件
            prop.load(in);
            //读取信息
            url = prop.getProperty("url");
            driverClass = prop.getProperty("driverClass");
            userName = prop.getProperty("user");
            password = prop.getProperty("password");

            //注册驱动程序
            Class.forName(driverClass);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获取数据库配置文件出错，使用默认配置!");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    * 不允许外部调用构造函数
    * */
    private Dao() {

        try {
            dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(driverClass);
            dataSource.setJdbcUrl(url);
            dataSource.setUser(userName);
            dataSource.setPassword(password);
            dataSource.setMaxStatements(180);
            dataSource.setMaxPoolSize(100);
            dataSource.setMinPoolSize(10);
            dataSource.setInitialPoolSize(3);
            dataSource.setAcquireIncrement(1);
            dataSource.setAutoCommitOnClose(true);
            dataSource.setAcquireRetryAttempts(30);
            dataSource.setIdleConnectionTestPeriod(1800);
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    * 获取当前类的单例对象
    * */
    public static Dao getInstance() {
        if (instance == null) {
            synchronized (Dao.class) {
                if (instance == null)
                    instance = new Dao();
            }
        }
        return instance;
    }

    /**
     * 打开数据库驱动连接
     */
    private Connection getConnection() {

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            if (conn.isClosed()) {
                reset();
                conn = dataSource.getConnection();
            }
        } catch (Exception e) {
            reset();
            try {
                conn = dataSource.getConnection();
            } catch (SQLException e1) {
                throw new RuntimeException(e1);
            }

        }
        try {
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    /**
     * 清理环境，关闭连接(顺序:后打开的先关闭)
     * ResultSet
     * Statement
     * Connection
     */
    private void close(Connection conn, Statement stmt, ResultSet rs) {
        closeResultSet(rs);
        closeStatement(stmt);
        closeConnection(conn);
    }

    /*
   * 关闭ResultSet
   * */
    private void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new RuntimeException(e1);
            }
        }
    }

    /*
    * 关闭Statement
    * */
    private void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    /*
    * 关闭Connection
    * */
    private void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 查询
     *
     * @param sql
     * @param paramsValue
     * @return 返回List<Map<String, String>> 结果集
     */
    public List<Map<String, String>> queryForListMap(String sql, Object[] paramsValue) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 返回的集合
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();

            // 1. 获取连接
            con = getConnection();
            // 2. 创建stmt对象
            pstmt = con.prepareStatement(sql);
            // 3. 获取占位符参数的个数， 并设置每个参数的值
            int count = pstmt.getParameterMetaData().getParameterCount();
            if (paramsValue != null && paramsValue.length > 0) {
                for (int i = 0; i < paramsValue.length; i++) {
                    pstmt.setObject(i + 1, paramsValue[i]);
                }
            }
            // 4. 执行查询
            rs = pstmt.executeQuery();
            // 5. 获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            // ---> 获取列的个数
            int columnCount = rsmd.getColumnCount();

            // 6. 遍历rs
            while (rs.next()) {
                ResultSetMetaData md = rs.getMetaData();
                int colNum = md.getColumnCount();
                Map<String, String> map = new HashMap<String, String>();
                for (int j = 1; j <= colNum; j++) {
                    String colName = md.getColumnLabel(j);
                    map.put(colName, rs.getString(colName));
                }
                list.add(map);
            }

            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, rs);
        }
    }

    /**
     * 查询的通用方法
     *
     * @param sql
     * @param paramsValue * @return  返回Map<String, String>
     */
    public Map<String, String> queryForMap(String sql, Object[] paramsValue) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            // 1. 获取连接
            con = getConnection();
            // 2. 创建stmt对象
            pstmt = con.prepareStatement(sql);
            // 3. 获取占位符参数的个数， 并设置每个参数的值
            int count = pstmt.getParameterMetaData().getParameterCount();
            if (paramsValue != null && paramsValue.length > 0) {
                for (int i = 0; i < paramsValue.length; i++) {
                    pstmt.setObject(i + 1, paramsValue[i]);
                }
            }
            // 4. 执行查询
            rs = pstmt.executeQuery();
            // 5. 获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            // ---> 获取列的个数
            int columnCount = rsmd.getColumnCount();
            Map<String, String> map = new HashMap<String, String>();
            // 6. 遍历rs
            while (rs.next()) {
                ResultSetMetaData md = rs.getMetaData();
                int colNum = md.getColumnCount();

                for (int j = 1; j <= colNum; j++) {
                    String colName = md.getColumnLabel(j);
                    map.put(colName, rs.getString(colName));
                }
                break;
            }

            return map;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, rs);
        }
    }

    /**
     * 查询的通用方法
     *
     * @param sql
     * @param paramsValue
     */
    public <T> List<T> queryForListObject(String sql, Object[] paramsValue, Class<T> clazz) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 返回的集合
            List<T> list = new ArrayList<T>();
            // 对象
            T t = null;

            // 1. 获取连接
            con = getConnection();
            // 2. 创建stmt对象
            pstmt = con.prepareStatement(sql);
            // 3. 获取占位符参数的个数， 并设置每个参数的值
            int count = pstmt.getParameterMetaData().getParameterCount();
            if (paramsValue != null && paramsValue.length > 0) {
                for (int i = 0; i < paramsValue.length; i++) {
                    pstmt.setObject(i + 1, paramsValue[i]);
                }
            }
            // 4. 执行查询
            rs = pstmt.executeQuery();
            // 5. 获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            // ---> 获取列的个数
            int columnCount = rsmd.getColumnCount();

            // 6. 遍历rs
            while (rs.next()) {
                // 要封装的对象
                t = clazz.newInstance();

                // 7. 遍历每一行的每一列, 封装数据
                for (int i = 0; i < columnCount; i++) {
                    // 获取每一列的列名称
                    String columnName = rsmd.getColumnName(i + 1);
                    // 获取每一列的列名称, 对应的值
                    Object value = rs.getObject(columnName);
                    // 封装： 设置到t对象的属性中  【BeanUtils组件】
                    BeanUtils.copyProperty(t, columnName, value);
                }

                // 把封装完毕的对象，添加到list集合中
                list.add(t);
            }

            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, rs);
        }
    }

    /**
     * 查询的通用方法
     *
     * @param sql
     * @param paramsValue
     */
    public <T> T queryForObject(String sql, Object[] paramsValue, Class<T> clazz) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            // 对象
            T t = null;

            // 1. 获取连接
            con = getConnection();
            // 2. 创建stmt对象
            pstmt = con.prepareStatement(sql);
            // 3. 获取占位符参数的个数， 并设置每个参数的值
            int count = pstmt.getParameterMetaData().getParameterCount();
            if (paramsValue != null && paramsValue.length > 0) {
                for (int i = 0; i < paramsValue.length; i++) {
                    pstmt.setObject(i + 1, paramsValue[i]);
                }
            }
            // 4. 执行查询
            rs = pstmt.executeQuery();
            // 5. 获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            // ---> 获取列的个数
            int columnCount = rsmd.getColumnCount();

            // 6. 遍历rs
            while (rs.next()) {
                // 要封装的对象
                t = clazz.newInstance();

                // 7. 遍历每一行的每一列, 封装数据
                for (int i = 0; i < columnCount; i++) {
                    // 获取每一列的列名称
                    String columnName = rsmd.getColumnName(i + 1);
                    // 获取每一列的列名称, 对应的值
                    Object value = rs.getObject(columnName);
                    // 封装： 设置到t对象的属性中  【BeanUtils组件】
                    BeanUtils.copyProperty(t, columnName, value);
                }
                break;
            }

            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, rs);
        }
    }

    /**
     * 更新的通用方法
     *
     * @param sql         更新的sql语句(update/insert/delete)
     * @param paramsValue sql语句中占位符对应的值(如果没有占位符，传入null)
     * @return 返回受影响的行数
     */
    public int execute(String sql, Object[] paramsValue) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取连接
            con = getConnection();
            // 创建执行命令的stmt对象
            pstmt = con.prepareStatement(sql);
            // 参数元数据： 得到占位符参数的个数
            int count = pstmt.getParameterMetaData().getParameterCount();

            // 设置占位符参数的值
            if (paramsValue != null && paramsValue.length > 0) {
                // 循环给参数赋值
                for (int i = 0; i < count; i++) {
                    pstmt.setObject(i + 1, paramsValue[i]);
                }
            }
            // 执行更新
            return pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, null);
        }
    }

    /**
     * 事务-更新的通用方法
     *
     * @param con         连接对象
     * @param sql         更新的sql语句(update/insert/delete)
     * @param paramsValue sql语句中占位符对应的值(如果没有占位符，传入null)
     * @return 返回受影响的行数
     */
    public int execute(Connection con, String sql, Object[] paramsValue) {
        PreparedStatement pstmt = null;
        try {
            // 创建执行命令的stmt对象
            pstmt = con.prepareStatement(sql);
            // 参数元数据： 得到占位符参数的个数
            int count = pstmt.getParameterMetaData().getParameterCount();
            // 设置占位符参数的值
            if (paramsValue != null && paramsValue.length > 0) {
                // 循环给参数赋值
                for (int i = 0; i < count; i++) {
                    pstmt.setObject(i + 1, paramsValue[i]);
                }
            }
            // 执行更新
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(pstmt);
        }
    }

    /**
     * 更新的通用方法
     *
     * @param sql         插入的sql语句(insert)
     * @param paramsValue sql语句中占位符对应的值(如果没有占位符，传入null)
     * @return 返回自增ID
     */
    public int executeAddGetID(String sql, Object[] paramsValue) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int autoIncKeyFromApi = -1;
        try {
            // 获取连接
            con = getConnection();
            // 创建执行命令的stmt对象
            pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            // 参数元数据： 得到占位符参数的个数
            int count = pstmt.getParameterMetaData().getParameterCount();

            // 设置占位符参数的值
            if (paramsValue != null && paramsValue.length > 0) {
                // 循环给参数赋值
                for (int i = 0; i < count; i++) {
                    pstmt.setObject(i + 1, paramsValue[i]);
                }
            }

            // 执行更新
            pstmt.executeUpdate();

            //获取自增ID
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                autoIncKeyFromApi = rs.getInt(1);
            }
            return autoIncKeyFromApi;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(con, pstmt, null);
        }
    }

    /**
     * 事务-更新的通用方法
     *
     * @param sql         插入的sql语句(insert)
     * @param paramsValue sql语句中占位符对应的值(如果没有占位符，传入null)
     * @return 返回自增ID
     */
    public int executeAddGetID(Connection con, String sql, Object[] paramsValue) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int autoIncKeyFromApi = -1;
        try {
            // 创建执行命令的stmt对象
            pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            // 参数元数据： 得到占位符参数的个数
            int count = pstmt.getParameterMetaData().getParameterCount();

            // 设置占位符参数的值
            if (paramsValue != null && paramsValue.length > 0) {
                // 循环给参数赋值
                for (int i = 0; i < count; i++) {
                    pstmt.setObject(i + 1, paramsValue[i]);
                }
            }
            // 执行更新
            pstmt.executeUpdate();

            //获取自增ID
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                autoIncKeyFromApi = rs.getInt(1);
            }
            return autoIncKeyFromApi;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeResultSet(rs);
            closeStatement(pstmt);
        }
    }

    /**
     * 开启事务
     * <p/>
     * (1,2,4,8) 暂时默认强制为2 ,给入的参数无效,无特殊需求请传入参数为2
     * TRANSACTION_NONE 不支持事务
     * 1 TRANSACTION_READ_UNCOMMITTED  	允许脏读取、不可重复的读和虚读。
     * 2 TRANSACTION_READ_COMMITTED   	读取未提交的数据是不允许的。允许不可重复的读和虚读。
     * 4 TRANSACTION_REPEATABLE_READ   	事务保证能够再次读取相同的数据而不会失败，但虚读仍会出现。
     * 8 TRANSACTION_SERIALIZABLE  		防止脏读、不可重复的读和虚读。(串行,禁止并发,严重影响性能)
     */
    public Connection beginTransaction() {
        try {
            Connection conn = getConnection();
            //conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            conn.setAutoCommit(false);
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    * 事务提交
    * */
    public void commit(Connection conn) {

        try {
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    /*
    * 事务回滚
    * */
    public void rollback(Connection conn) {

        try {
            conn.rollback();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(conn);
        }
    }

    /*
    * 关闭连接
    * */
    private void reset() {
        try {
            dataSource.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
