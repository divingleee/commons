package com.gree.bdc.dept4.commons.http;

import java.io.Serializable;
import java.util.List;

/**
 * @author: 260222
 * @date: 2018/12/31 17:15
 * @description:
 */
public class PageResult<T> implements Serializable {

    /**
     * 当前是第几页
     */
    Long currentPage;
    /**
     * 每页的数量
     */
    Long pageSize;
    /**
     * 一共多少页
     */
    Long totalPages;
    /**
     * 所有页的总数量
     */
    Long totalSize;
    /**
     * 当前页的数据
     */
    List<T> list;


    public PageResult(Long currentPage, Long pageSize, Long totalPages, Long totalSize, List<T> list) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalSize = totalSize;
        this.list = list;
    }

    public static <T> PageResult build(Long currentPage, Long pageSize, Long totalPages, Long totalSize, List<T> list) {
        return new PageResult<T>(currentPage, pageSize, totalPages, totalSize, list);
    }
}
