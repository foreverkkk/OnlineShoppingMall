package com.zk.service;

import com.zk.dao.AdminCategoryDao;
import com.zk.pojo.Category;
import com.zk.pojo.Page;

import java.util.List;
import java.util.Map;

/**
 * @author zk
 */
public class AdminCategoryService {

    private AdminCategoryDao adminCategoryDao = new AdminCategoryDao();

    /**
     * 调用 dao 进行添加一条操作
     * @param category
     */
    public void add(Category category){
        adminCategoryDao.add(category);
    }

    /**
     * 根据分类名查找是否存有该分类
     */
    public boolean validateName(String name){
        Category category = adminCategoryDao.findByName(name);
        if (category == null){
            return false;
        }
        return true;
    }

    /**
     *  获取商品列表
     * @return
     */
    public List<Map<String , Object>> findAll(){
        // 调用 dao 层的方法
        return adminCategoryDao.findAll();
    }

    /**
     *  获取当前页对应的 page 对象
     */
    public Page findAll(int currentPage){
        /**
         *  调用 dao 层 findTotalCount 方法获取总记录数
         *  创建 Page 对象
         *  根据起始记录和每页显示多少条记录，调用 dao 层 findAll 方法来获取当前页所有的记录
         *  把从数据库读取的记录集给 Page 对象赋值，并返回Page对象
         */
        // 获取总记录数
        int totalCount = adminCategoryDao.findTotalCount();
        // 创建 Page 对象
        Page page = new Page(currentPage, totalCount);
        // 根据起始记录和每页显示多少条记录，调用 dao 层 findAll 方法来获取当前页所有的记录
        List<Map<String, Object>> list = adminCategoryDao.findAll(page.getStartIndex(), page.getPageSize());
        page.setList(list);
        return page;
    }
    /**
     * 单个删除商品
     */
    public void deleteById(int id){
        // 调用 dao 层方法
        adminCategoryDao.deleteById(id);
    }
    /**
     *  批量删除商品
     */
    public void deleteMore(String []id){
        adminCategoryDao.deleteMore(id);
    }

    /**
     * 根据 id 获取一条记录
     */
    public Map<String,Object> findById(int id){
        return adminCategoryDao.findById(id);
    }

    /**
     * 修改信息
     * @param category
     */
    public void update(Category category){
        adminCategoryDao.update(category);
    }
}
