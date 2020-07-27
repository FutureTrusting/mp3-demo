package com.fengwenyi.mp3demo.dto;


import com.fengwenyi.mp3demo.enums.ErrorCodeEnum;

import java.util.List;

/**
 * @Author: Jiangyongjie
 * @Date: 2018/11/23 10:52
 * @Desc:   前端分页列表
 */

public class PageTableDo<T> {

    private Integer pageSize;//每页数据量

    private Integer pageNum;//页码

    private Integer start;//开始

    private Integer total;//总记录数

    private List<T> data;//数据

    public PageTableDo() {}

    public PageTableDo(Integer pageSize, Integer pageNum) {
        if(pageSize == null || pageNum == null || pageNum < 1){
            pageSize = 0;
            pageNum = 1;
        }
        if(pageNum < 0 || pageSize < 1){
            throw ErrorCodeEnum.PARAM_ERROR.createException("参数错误!");
        }
        pageSize = pageSize > 200 ? 200 : pageSize;
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.start = (pageNum - 1) * pageSize;

    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
