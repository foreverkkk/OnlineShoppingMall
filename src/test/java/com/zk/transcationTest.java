package com.zk;

import com.zk.utils.JdbcUtil;
import org.junit.Test;

import java.sql.SQLException;

public class transcationTest {

    @Test
    public void transcation(){
        String sql1 = "update bankaccount set account=account-10 where id = 1";
        String sql2 = "update bankaccount set account=account+10 where id = 2";
        // 开启事务
        try {
            JdbcUtil.openTranscation();
            JdbcUtil.update(sql1);
            JdbcUtil.update(sql2);
            JdbcUtil.commitTranscation();
        } catch (SQLException throwables) {
            try {
                JdbcUtil.rollbackTranscation();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
