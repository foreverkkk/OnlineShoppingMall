package com.zk.utils;

//import java.io.FileReader;
//import java.io.IOException;
//import java.net.URL;
//import java.sql.*;
//import java.util.Properties;
//
//public class JdbcUtil(){
//    private static String url;
//    private static String user;
//    private static String password;
//    private static String driver;
//    /**
//     * 文件的读取只需要一次
//     */
//    static {
//        //读取资源文件，获取值
//        try {
//            //创建Properties集合对象
//            Properties pro = new Properties();
//            //获取src路径下的文件方式 ---> ClassLoad 类加载器
//            ClassLoader classLoader = JdbcUtil.class.getClassLoader();
//            URL res = classLoader.getResource("jdbc.properties");
//            String path = res.getPath();
//            //加载文件
//            pro.load(new FileReader(path));
//            //获取数据，赋值
//            url = pro.getProperty("url");
//            user = pro.getProperty("user");
//            password = pro.getProperty("password");
//            driver = pro.getProperty("driver");
//            Class.forName("driver");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 获取连接
//     * @return 连接对象
//     */
//    public static Connection getConnection(){
//        try {
//            return DriverManager.getConnection(url,user,password);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    /**
//     * 释放资源
//     */
//    public static void close(Statement stmt, Connection conn){
//        if(stmt != null){
//            try {
//                stmt.close();
//            } catch (SQLException E) {
//                E.printStackTrace();
//            }
//        }
//        if(conn != null){
//            try {
//                conn.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//    }
//    public static void close(ResultSet rs, Statement stmt, Connection conn){
//        if(rs != null){
//            try {
//                rs.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        if(stmt != null){
//            try {
//                stmt.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        if(conn != null){
//            try {
//                conn.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//    }
//    public static void update(String sql , Object ... params){
//        try {
//            PreparedStatement preparedStatement = JdbcUtil.getConnection().prepareStatement(sql);
//            if (params!=null){
//                for (int i = 0; i < params.length; i++) {
//                    preparedStatement.setObject(i+1,params[i]);
//                }
//            }
//            preparedStatement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//}





//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.net.URL;
//import java.sql.*;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//
//public class JdbcUtil {
//    private static String url;
//    private static String user;
//    private static String password;
//    private static String driver;
//    // 增删改操作
//    public static void update(String sql, Object... params) {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            Properties properties = new Properties();
//            ClassLoader classLoader = JdbcUtil.class.getClassLoader();
//            URL resource = classLoader.getResource("jdbc.properties");
//            String path = resource.getPath();
//            properties.load(new FileReader(path));
//            //获取数据，赋值
//            url = properties.getProperty("url");
//            user = properties.getProperty("user");
//            password = properties.getProperty("password");
//            driver = properties.getProperty("driver");
//
//            Class.forName("driver");
//            connection = DriverManager.getConnection(url, user, password);
//            preparedStatement = connection.prepareStatement(sql);
//            if (params != null) {
//                for (int i = 0; i < params.length; i++) {
//                    preparedStatement.setObject(i + 1, params[i]);
//                }
//            }
//            preparedStatement.executeUpdate();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    preparedStatement.close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                } finally {
//                    if (connection != null) {
//                        try {
//                            connection.close();
//                        } catch (SQLException throwables) {
//                            throwables.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * 增加操作，需要返回自增的主键
//     * @param sql
//     * @param params
//     * @return
//     */
//    public static Object insert(String sql , Object ... params) throws SQLException {
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            Properties properties = new Properties();
//            ClassLoader classLoader = JdbcUtil.class.getClassLoader();
//            URL resource = classLoader.getResource("jdbc.properties");
//            String path = resource.getPath();
//            properties.load(new FileReader(path));
//            //获取数据，赋值
//            url = properties.getProperty("url");
//            user = properties.getProperty("user");
//            password = properties.getProperty("password");
//            driver = properties.getProperty("driver");
//
//            Class.forName("driver");
//            connection = DriverManager.getConnection(url, user, password);
//            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            if (params != null) {
//                for (int i = 0; i < params.length; i++) {
//                    preparedStatement.setObject(i + 1, params[i]);
//                }
//            }
//            preparedStatement.executeUpdate();
//            // 获取主键
//            resultSet = preparedStatement.getGeneratedKeys();
//            Object key = null;
//            if (resultSet.next()) {
//                key = resultSet.getObject(1);
//            }
//            return key;
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            if (preparedStatement != null) {
//                try {
//                    if (resultSet != null) {
//                        resultSet.close();
//                    }
//                } catch (SQLException e) {
//                    throw new RuntimeException();
//                } finally {
//                    try {
//                        preparedStatement.close();
//                    } catch (SQLException throwables) {
//                        throwables.printStackTrace();
//                    } finally {
//                        if (connection != null) {
//                            try {
//                                connection.close();
//                            } catch (SQLException throwables) {
//                                throwables.printStackTrace();
//                            }
//                        }
//                    }
//                }
//            }
//            return null;
//        }
//    }
//
//    // 查找操作
//    public static List<Map<String,Object>> select(String sql , Object ... params){
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            Properties properties = new Properties();
//            ClassLoader classLoader = JdbcUtil.class.getClassLoader();
//            URL resource = classLoader.getResource("jdbc.properties");
//            String path = resource.getPath();
//            properties.load(new FileReader(path));
//            //获取数据，赋值
//            url = properties.getProperty("url");
//            user = properties.getProperty("user");
//            password = properties.getProperty("password");
//            driver = properties.getProperty("driver");
//
//            Class.forName("driver");
//            connection = DriverManager.getConnection(url, user, password);
//            preparedStatement = connection.prepareStatement(sql);
//        return null;
//    } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }




import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.*;

/**
 * @author zk
 */
public class JdbcUtil {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;


    /**
     * ThreadLocal--> 本地线程  (只能被该线程独自占有，防止并发被抢占)
     * 事务专用的连接
     */
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
//    static {
//        //读取资源文件，获取值
//        try {
//            //创建Properties集合对象
//            Properties pro = new Properties();
//            //获取src路径下的文件方式 ---> ClassLoad 类加载器
//            ClassLoader classLoader = JdbcUtil.class.getClassLoader();
//            URL res = classLoader.getResource("jdbc.properties");
//            String path = res.getPath();
//            //加载文件
//            pro.load(new FileReader(path));
//            //获取数据，赋值
//            url = pro.getProperty("url");
//            user = pro.getProperty("user");
//            password = pro.getProperty("password");
//            driver = pro.getProperty("driver");
//            Class.forName("driver");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
    static {
    InputStream inputStream = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            Class.forName(driver);
        } catch (Exception e) {
           e.printStackTrace();
        }
}
    /**
     *     获取数据库连接
     *     使用事务的 Connection ，如果不是事务，创建一个新的 Connection
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = tl.get();
        if (connection != null){
            // 已经开启过事务
            return connection;
        }
        return DriverManager.getConnection(url,user,password);
    }


    /**
     *  开启事务的方法
     */
    public static void openTranscation() throws SQLException {
        // 从 tl 中获取 Connection 对象 有值表示已经开启过线程-->抛出异常 相反，则要创建 Connection 对象，开启事务，把事务保存在tl中
        Connection connection = tl.get();
        if(connection != null){
            throw new SQLException("已经开启过事务！请勿重复开启！");
        }else {
            connection = getConnection();
            // 开启事务
            connection.setAutoCommit(false);
            // 将事务保存到 tl 中
            tl.set(connection);
        }
    }
    /**
     *  提交事务
     */
    public static void commitTranscation() throws SQLException {
        // 获取 Connection 对象，如果没有该对象，不存在事务，抛出异常  相反，提交事务，关闭连接，tl 中的事务移除
        Connection connection = tl.get();
        if (connection == null){
            throw new SQLException("不存在事务，无法提交！");
        }else {
            connection.commit();
            connection.close();
            connection = null;
            tl.remove();
        }
    }
    /**
     * 回滚事务
     */
    public static void rollbackTranscation() throws SQLException {
        Connection connection = tl.get();
        if (connection == null){
            throw new SQLException("不存在事务，无法回滚！");
        }else {
            connection.rollback();
            connection.close();
            connection = null;
            tl.remove();
        }
    }


    /**
     * 查找操作
     */
    public static List<Map<String,Object>> select(String sql , Object ... params){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Map<String,Object>> list = null;
        try{
            // 获取数据库连接
            // 在进行事务操作的时候，该行代码要进行改写 使用事务的 Connection ，如果不是事务，创建一个新的 Connection,在获取Connection连接时判断修改
            connection = getConnection();
            // 获得Statement对象
            preparedStatement = connection.prepareStatement(sql);
            // 设置参数
            fillStatement(preparedStatement, params);
            // 使用Statement执行语句
            resultSet = preparedStatement.executeQuery();
            list = RsToList(resultSet);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            // 进行事务操作时，如果是事务 connection 不能关闭，如果不是事务，关闭connection
            close(resultSet,preparedStatement,connection);
        }
        return list;
    }
    private static void fillStatement(PreparedStatement preparedStatement, Object[] params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
    }
    /**
     *  将 ResultSet 转换为一个 List
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private static List<Map<String, Object>> RsToList(ResultSet resultSet) throws SQLException {
        List<Map<String , Object>> list = new ArrayList<>();
        // 获取表结构
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        // 循环读取Map中的每一条记录
        while (resultSet.next()){
            // 将每条记录放到一个Map中
            Map<String,Object> map = new HashMap<String , Object>(16,0.75f);
            // resultSetMetaData.getColumnCount 获取表的列数
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                // 逐个字段读取出来放到Map中
                map.put(resultSetMetaData.getColumnName(i) , resultSet.getObject(i));
            }
            list.add(map);
        }
        return  list;
    }
    /**
     * 用于返回单值的操作
     */
    public static <T> T selectScalar(String sql , Object ... params){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        T result = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            fillStatement(preparedStatement , params);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                result = (T) resultSet.getObject(1);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(resultSet , preparedStatement , connection);
        }
    }


    /**
     *  增删改操作 , 无返回值
     */
    public static void update(String sql , Object ... params){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // 获取数据库连接
            connection = getConnection();
            // 获取 Statement 对象
            preparedStatement = connection.prepareStatement(sql);
            // 设置参数
            fillStatement(preparedStatement, params);
            // 使用 Statement 中的 executeUpdate 方法执行
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            close(preparedStatement,connection);
        }
    }
    /**
     *  增加操作，返回自增主键
     */
    public static Object insert(String sql , Object ... params){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Object key = null;
        try {
            // 获取数据库连接
            connection = getConnection();
            // 获取 Statement 对象
            preparedStatement = connection.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
            // 设置参数
            fillStatement(preparedStatement, params);
            // 使用 Statement 中的 executeUpdate 方法执行
            preparedStatement.executeUpdate();
            // 获取主键
            resultSet = preparedStatement.getGeneratedKeys();
//            Object key = null;
            if (resultSet.next()){
                key = resultSet.getObject(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            close(resultSet,preparedStatement,connection);
        }
        return key;
    }


    /**
     * 释放资源
     */
    public static void close(Statement stmt, Connection conn){
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException E) {
                E.printStackTrace();
            }
        }
        Connection tconnection = tl.get();
        // 是事务
        if (tconnection == conn){
            return;
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    /**
     * 批处理
     *      用于 update insert delete 这些批处理操作
     */
    public static int[] updateBatch(String sql , Object [][]params){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            // 设置参数
            if (params != null){
                for (int i = 0; i < params.length; i++) {
                    // 设置每条记录的参数
                    fillStatement(preparedStatement , params[i]);
                    preparedStatement.addBatch();
                }
            }
            return preparedStatement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(preparedStatement , connection);
        }
    }
    /**
     * 批处理
     *      用于 insert 批处理操作返回主键的操作
     */
    public static <T> List<T> insertBatch(String sql , Object [][]params){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
            // 设置参数
            if (params != null){
                for (int i = 0; i < params.length; i++) {
                    // 设置每条记录的参数
                    fillStatement(preparedStatement , params[i]);
                    preparedStatement.addBatch();
                }
            }
            preparedStatement.executeBatch();
            resultSet = preparedStatement.getGeneratedKeys();
            List<T> list = new ArrayList<T>();
            while (resultSet.next()){
                list.add((T) resultSet.getObject(1));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(preparedStatement , connection);
        }
    }
}
