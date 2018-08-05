package com.serviceMarket.pojo;

import java.util.Date;

public class Token {
    private String id;

    private Date expertime;

    private Boolean isshoper;

    private Boolean ishelper;

    private Integer userid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getExpertime() {
        return expertime;
    }

    public void setExpertime(Date expertime) {
        this.expertime = expertime;
    }

    public Boolean getIsshoper() {
        return isshoper;
    }

    public void setIsshoper(Boolean isshoper) {
        this.isshoper = isshoper;
    }

    public Boolean getIshelper() {
        return ishelper;
    }

    public void setIshelper(Boolean ishelper) {
        this.ishelper = ishelper;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}