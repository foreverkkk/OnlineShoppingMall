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
}
