package com.zk.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author zk
 */
public class DruidUtil {
    private static DataSource ds;
    static {
        Properties pro = new Properties();
        try {
            //加载配置文件
            pro.load(DruidUtil.class.getClassLoader().getResourceAsStream("druid.properties"));
            //获取DataSource
            try {
                ds = DruidDataSourceFactory.createDataSource(pro);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return 获取连接
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    /**
     * 释放资源
     */
    public static void close(Statement stmt, Connection conn){
        if(stmt != null)
        {
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
    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if(rs != null)
        {
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
     * 获取数据库连接池
     */
    public static DataSource getDataSource(){
        return ds;
    }

    /**
     * 数据库的增删改查操作
     */
//    public static void update(String sql , Object ... params){
//        if(params != null){
//            for (int i = 0; i < params.length; i++) {
//
//            }
//        }
//    }
}
