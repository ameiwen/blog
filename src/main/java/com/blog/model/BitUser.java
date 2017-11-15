package com.blog.model;

import java.math.BigDecimal;
import java.util.Date;

public class BitUser {
    private Long id;

    private String username;

    private String mobile;

    private String qq;

    private String password;

    private String realname;

    private String email;

    private String headimg;

    private String idcard;

    private String moneypwd;

    private String fromsite;

    private BigDecimal money;

    private String idcardtype;

    private String ip;

    private Date registertime;

    private Date birthday;

    private String status;

    private String realstatus;

    private Date updatetime;

    private String googlekey;

    private Long approvenum;

    private BigDecimal praisedegree;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg == null ? null : headimg.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getMoneypwd() {
        return moneypwd;
    }

    public void setMoneypwd(String moneypwd) {
        this.moneypwd = moneypwd == null ? null : moneypwd.trim();
    }

    public String getFromsite() {
        return fromsite;
    }

    public void setFromsite(String fromsite) {
        this.fromsite = fromsite == null ? null : fromsite.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getIdcardtype() {
        return idcardtype;
    }

    public void setIdcardtype(String idcardtype) {
        this.idcardtype = idcardtype == null ? null : idcardtype.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRealstatus() {
        return realstatus;
    }

    public void setRealstatus(String realstatus) {
        this.realstatus = realstatus == null ? null : realstatus.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getGooglekey() {
        return googlekey;
    }

    public void setGooglekey(String googlekey) {
        this.googlekey = googlekey == null ? null : googlekey.trim();
    }

    public Long getApprovenum() {
        return approvenum;
    }

    public void setApprovenum(Long approvenum) {
        this.approvenum = approvenum;
    }

    public BigDecimal getPraisedegree() {
        return praisedegree;
    }

    public void setPraisedegree(BigDecimal praisedegree) {
        this.praisedegree = praisedegree;
    }
}