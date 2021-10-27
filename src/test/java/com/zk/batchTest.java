package com.zk;

import com.zk.utils.JdbcUtil;
import org.junit.Test;

public class batchTest {

    @Test
    public void batch(){
        String sql = "insert into bankaccount value(null,?,?)";
        Object params[][] = { {"zkk",100} , {"lyy",100} };
        JdbcUtil.updateBatch(sql , params);
    }

    @Test
    public void insertBatch(){
        String sql = "insert into bankaccount value(null,?,?)";
        Object params[][] = { {"forever",100} , {"curry",100} };
        System.out.println(JdbcUtil.updateBatch(sql, params));
    }
}
