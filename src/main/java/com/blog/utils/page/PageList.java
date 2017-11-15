package com.blog.utils.page;

import java.util.ArrayList;
import java.util.List;

public class PageList<T> {
    private Page page;
    private List<T> resultList = new ArrayList();
    private boolean hasNext = false;
    private int totalCount;

    public PageList() {
    }

    public Page getPage() {
        return this.page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<T> getResultList() {
        return this.resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public boolean getHasNext() {
        return this.hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        return (this.totalCount + this.page.getPageSize() - 1) / this.page.getPageSize();
    }
}