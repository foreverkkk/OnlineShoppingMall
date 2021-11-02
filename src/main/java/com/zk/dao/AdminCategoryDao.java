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

    /**
     *  获取商品所有记录
     * @return
     */
    public List<Map<String , Object>> findAll(){
        String sql = "select * from category";
        return JdbcUtil.select(sql);
    }

    /**
     *  获取表中总记录数
     */
    public int findTotalCount(){
        String sql = "select count(*) from category";
        return ((Number)JdbcUtil.selectScalar(sql)).intValue();
    }

    /**
     *  根据起始记录索引和读取记录个数，获取分类记录集合
     */
    public List<Map<String , Object>> findAll(int startIndex , int size){
        String sql = "select * from category limit ?,?";
        Object []params = {
                startIndex , size
        };
        return JdbcUtil.select(sql , params);
    }

    /**
     * 单个删除商品
     */
    public void deleteById(int id){
        String sql = "delete from category where categoryid = ?";
        JdbcUtil.update(sql , id);
    }
    /**
     * 批量删除商品
     */
    public void deleteMore(String []id){
        String sql = "delete from category where categoryid in (";
        StringBuilder str = new StringBuilder("");
        for (int i = 0; i < id.length; i++){
            // i == id.length-1 表示为最后一个
            if (i == id.length-1){
                str.append("?)");
            }else {
                str.append("?,");
            }
        }
        JdbcUtil.update(sql+str.toString() , id);
    }

    /**
     * 根据 id 获取一条记录
     */
    public Map<String,Object> findById(int id){
        String sql = "select * from category where categoryid = ?";
        return JdbcUtil.select(sql,id).get(0);
    }
    /**
     * 修改商品信息 , 通过 Category 进行更新
     */
    public void update(Category category){
        String sql = "update category set name=?,sort=? where categoryid=?";
        Object []params = {
                category.getName(),category.getSort(),category.getCategoryid()
        };
        JdbcUtil.update(sql , params);
    }
}
