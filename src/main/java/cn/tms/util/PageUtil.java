package cn.tms.util;


import cn.tms.entity.UserInfo;

import java.util.List;

/**
 * Created by Happy on 2017-08-23.
 */
public class PageUtil<T> {
    private Integer pageSize; //每页显示记录数
    private Integer pageIndex;  //当前页码
    private Integer totalRecords; //总记录数
    private Integer totalPages; //总页数
    private List<T> list; //数据

    private String userName; //查询条件

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
