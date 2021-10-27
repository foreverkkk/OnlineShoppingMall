package com.zk;

import com.zk.utils.JdbcUtil;
import org.junit.Test;

public class scalarTest {

    @Test
    public void selectScalar(){
        String sql = "select count(*) from category";
        Number result = JdbcUtil.selectScalar(sql);
        System.out.println(result.intValue());
    }
}
