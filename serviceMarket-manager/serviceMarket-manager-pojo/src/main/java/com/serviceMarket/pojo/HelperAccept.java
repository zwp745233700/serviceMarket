package com.serviceMarket.pojo;

import java.util.Date;

public class HelperAccept {
    private Integer id;

    private Integer helperId;

    private Integer helperTableId;

    private Date deadTime;

    private Date acceptTime;

    private String phone;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHelperId() {
        return helperId;
    }

    public void setHelperId(Integer helperId) {
        this.helperId = helperId;
    }

    public Integer getHelperTableId() {
        return helperTableId;
    }

    public void setHelperTableId(Integer helperTableId) {
        this.helperTableId = helperTableId;
    }

    public Date getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(Date deadTime) {
        this.deadTime = deadTime;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}