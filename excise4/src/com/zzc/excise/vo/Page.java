package com.zzc.excise.vo;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: Page
 * @Author: zzc
 * @CreateTime: 2020/10/28 17:19
 * @Description: 将页面显示参数封装成对象
 */

public class Page implements Serializable {
    private int pageSize;      // 页面显示条数
    private int pageNumber;     // 显示的页数
    private String sort;        // 排序列名
    private String order;       // 排序方式

    public Page() {
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
