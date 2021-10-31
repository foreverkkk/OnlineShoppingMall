package com.zk;

import com.zk.utils.JdbcUtil;
import org.junit.Test;

public class JdbcUtilTest {

    @Test
    public void insertTest(){
        String sql = "insert into category value(null,?,?)";
        Object []params = {
                "袜子" , 3
        };
        JdbcUtil.insert(sql , params);
    }
}
