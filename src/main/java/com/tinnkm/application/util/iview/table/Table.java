package com.tinnkm.application.util.iview.table;

import java.util.List;

/**
 * @author quantdo_wh02
 * @version 1.0
 * @classname Table
 * @description iview组件table模型数据
 * @date 2018/6/12 9:02
 **/
public class Table<T> {
    /**
     * 总数
     */
    private int total;
    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 数据
     */
    private T data;

    /**
     * 列头
     */
    private List<TableColumn> columns;
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
