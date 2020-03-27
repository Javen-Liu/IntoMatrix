package com.matrix.entity;

/**
 * @author Javen-Liu
 * @version 1.0
 * @date 2020/3/24 19:58
 * @github https://github.com/Javen-Liu
 */
public class PageBean {
    /**
     * 当前页面数，从1开始
     */
    private int page;
    /**
     * 页面大小
     */
    private int pageSize;

    public PageBean(int page, int pageSize){
        this.page = page;
        this.pageSize = pageSize;
    }

    public PageBean(){

    }

    /**
     * 计算从那页开始查询显示
     * @return 返回开始查询的起点
     */
    public int getStart(){
        return (page - 1)*pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
