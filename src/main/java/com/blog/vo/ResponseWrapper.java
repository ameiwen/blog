package com.blog.vo;

import java.util.HashMap;
import java.util.Map;

public class ResponseWrapper {
    private int status;
    private Map<String, Object> dataWrapper = new HashMap();
    private String msg;
    private boolean success;
    private String sourceUrl;

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    private int totalpage;

    public ResponseWrapper() {
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void addAttribute(String key, Object val) {
        this.dataWrapper.put(key, val);
    }

    public void removeAttribute(String key) {
        this.dataWrapper.remove(key);
    }

    public Map<String, Object> getDataWrapper() {
        return this.dataWrapper;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSourceUrl() {
        return this.sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

}