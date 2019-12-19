package com.hzvtc.lujc.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //当前页的数量
    private int size;

    //总记录数
    private long total;
    //总页数
    private int pages;
    //结果集
    private List<T> list;

    public PageResult() {
    }

    public PageResult(int pageNum, int pageSize, int size, long total, int pages, List<T> list) {
        super();
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.size = size;
        this.total = total;
        this.pages = pages;
        this.list = list;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    public static PageResult getNoneResult(int size) {
        PageResult pageResult = new PageResult(1, 1, size, 0, 1, new ArrayList());
        return pageResult;
    }


}