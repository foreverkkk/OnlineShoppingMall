package com.zk.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zk
 *  Category 实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private int categoryid;
    private String name;
    private int sort;

//    public int getCategoryid() {
//        return categoryid;
//    }
//
//    public void setCategoryid(int categoryid) {
//        this.categoryid = categoryid;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getSort() {
//        return sort;
//    }
//
//    public void setSort(int sort) {
//        this.sort = sort;
//    }
}
