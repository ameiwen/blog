package com.blog.utils.page;

public class Page {
    private int pageSize = 20;
    private int pageNo = 1;
    private String sortName;
    private String sortDirect;
    private int start;

    public Page() {
    }

    public int getStartIndex() {
        return (this.pageNo - 1) * this.pageSize;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getSortName() {
        return this.sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortDirect() {
        return this.sortDirect;
    }

    public void setSortDirect(String sortDirect) {
        this.sortDirect = sortDirect;
    }

    public int getStart() {
        return this.start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}

