package com.zk;

import com.zk.utils.JdbcUtil;
import org.junit.Test;

public class JdbcUtilTest {

    @Test
    public void insertTest(){
        String sql = "insert into category value(null,?,?)";
        Object []params = {
                "裤子" , 1
        };
        JdbcUtil.insert(sql , params);
    }
}
