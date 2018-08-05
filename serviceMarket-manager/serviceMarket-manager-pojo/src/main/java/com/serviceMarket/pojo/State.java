package com.serviceMarket.pojo;

import java.util.Date;

public class State {
    private Integer id;

    private Integer helperTableId;

    private Integer helperId;

    private Integer userAcceptState;

    private Integer arriveState;

    private Date createTime;

    private Date arriveTime;

    private String appraise;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHelperTableId() {
        return helperTableId;
    }

    public void setHelperTableId(Integer helperTableId) {
        this.helperTableId = helperTableId;
    }

    public Integer getHelperId() {
        return helperId;
    }

    public void setHelperId(Integer helperId) {
        this.helperId = helperId;
    }

    public Integer getUserAcceptState() {
        return userAcceptState;
    }

    public void setUserAcceptState(Integer userAcceptState) {
        this.userAcceptState = userAcceptState;
    }

    public Integer getArriveState() {
        return arriveState;
    }

    public void setArriveState(Integer arriveState) {
        this.arriveState = arriveState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise == null ? null : appraise.trim();
    }
}