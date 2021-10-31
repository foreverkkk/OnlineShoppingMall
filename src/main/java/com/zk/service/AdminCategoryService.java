package com.zk.service;

import com.zk.dao.AdminCategoryDao;
import com.zk.pojo.Category;

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
}
