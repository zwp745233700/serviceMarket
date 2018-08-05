package com.serviceMarket.pojo;

public class Permissions {
    private Integer id;

    private String isShop;

    private String isHelper;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsShop() {
        return isShop;
    }

    public void setIsShop(String isShop) {
        this.isShop = isShop == null ? null : isShop.trim();
    }

    public String getIsHelper() {
        return isHelper;
    }

    public void setIsHelper(String isHelper) {
        this.isHelper = isHelper == null ? null : isHelper.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}