package com.zk.pojo;

import java.util.List;
import java.util.Map;
/**
 * @author zk
 */
public class Page {
    /**
     *      pageSize:        --> 设置一个页面显示多少条记录
     *      totalSize:       --> 数据库中的所有记录，从数据库中读取   *** 在构造一个 Page 对象时就应该已经确定 （创建构造方法）
     *      currentPage:     --> 当前页码                        *** 在构造一个 Page 对象时就应该已经确定 （创建构造方法）
     *      totalPage:       --> 共有多少页
     *      list:            --> 当前页面显示的记录集
     *      startIndex:      --> 要读取记录的起始值
     *      num:             --> 页码列表的个数
     *      start:           --> 页码列表的起始值
     *      end:             --> 页码列表的终止值
     */
    private int pageSize = 3;
    private int totalSize;
    private int currentPage;
    private int totalPage;
    private List<Map<String , Object>> list;
    private int startIndex;
    private int num = 6;
//    private int start;
//    private int end;

    public Page(int currentPage , int totalSize){
        this.totalSize = totalSize;
        // 设置当前页
        setCurrentPage(currentPage);

    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

//    public void setStart(int start) {
//        this.start = start;
//    }
//
//    public void setEnd(int end) {
//        this.end = end;
//    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        /**
         *  判断当前页码是否合法
         *  页码 < 1 默认值为 1
         *  当前页码 > 总页数 设置为总页数
         */
        int totalPage = getTotalPage();
        if (currentPage < 1){
            currentPage = 1;
        }
        if (currentPage > totalPage){
            currentPage = totalPage;
        }
        this.currentPage = currentPage;
    }

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    public int getNum() {
        return num;
    }

    /**
     *  共有多少页 通过计算得到
     *    通过 数据库中所有的数据对每一页要显示的数目取余
     * @return
     */
    public int getTotalPage() {
        int totalPage = (totalSize % pageSize == 0)?(totalSize/pageSize):(totalSize/pageSize + 1);
        return totalPage;
    }

    /**
     *  计算读取记录的起始值
     * @return
     */
    public int getStartIndex() {
        int startIndex = (currentPage - 1) * pageSize;
        if (startIndex < 0){
            startIndex = 0;
        }
        return startIndex;
    }

    /**
     *  获取页码列表的起始值
     * @return
     */
    public int getStart() {
        int start = currentPage - num/2 + 1;
        if (start < 1){
            start = 1;
        }
        return start;
    }

    /**
     *  获取页码列表的终止值
     * @return
     */
    public int getEnd() {
        int end = getStart() + num - 1;
        int totalPage = getTotalPage();
        if (end > totalPage){
            end = totalPage;
        }
        return end;
    }
}
