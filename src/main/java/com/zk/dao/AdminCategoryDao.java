package com.zk.dao;

import com.zk.pojo.Category;
import com.zk.utils.JdbcUtil;

import java.util.List;
import java.util.Map;

/**
 * @author zk
 */
public class AdminCategoryDao {
    /**
     *  添加一条记录
     * @param category
     */
    public void add(Category category){
        String sql = "insert into category values(null,?,?)";
        Object []params = {
                category.getName(),
                category.getSort()
        };
        // 返回自增的主键
        Number n = (Number) JdbcUtil.insert(sql, params);
        category.setCategoryid(n.intValue());
    }

    /**
     * 根据 name 值查找分类，返回 category 对象
     */
    public Category findByName(String name){
        Category category = null;
        String sql = "select * from category where name = ?";
        List<Map<String , Object>> list = JdbcUtil.select(sql , name);
        if (list.size() > 0){
            category = new Category();
            category.setCategoryid(Integer.parseInt(list.get(0).get("categoryid").toString()));
            category.setName(name);
            category.setSort(Integer.parseInt((list.get(0).get("sort").toString())));

        }
        return category;
    }
}
