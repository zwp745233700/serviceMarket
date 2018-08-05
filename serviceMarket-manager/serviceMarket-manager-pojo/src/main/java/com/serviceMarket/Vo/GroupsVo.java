package com.serviceMarket.Vo;

import java.util.List;

import com.serviceMarket.pojo.UserGroup;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2018年1月31日 下午11:09:16 
* 类说明 团购Vo
*/

public class GroupsVo {
	private Integer id;
	private Integer peopleNum;//满足人数
	private String startTime;
	private String endTime;
	private Integer originalPrice;//原价
	private Integer groupPrice;//团购价
	private Integer limitNum;//限买数量
	private Integer surplus;//剩余量
	
	private ShowShopListVo shopVo;
	
	private List<UserGroupVo> userGroupVo;
	private List<GroupGoodVo> goodVo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPeopleNum() {
		return peopleNum;
	}
	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}
	public Integer getGroupPrice() {
		return groupPrice;
	}
	public void setGroupPrice(Integer groupPrice) {
		this.groupPrice = groupPrice;
	}
	public Integer getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}
	public Integer getSurplus() {
		return surplus;
	}
	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}
	public ShowShopListVo getShopVo() {
		return shopVo;
	}
	public void setShopVo(ShowShopListVo shopVo) {
		this.shopVo = shopVo;
	}
	public List<GroupGoodVo> getGoodVo() {
		return goodVo;
	}
	public void setGoodVo(List<GroupGoodVo> goodVo) {
		this.goodVo = goodVo;
	}
	public List<UserGroupVo> getUserGroupVo() {
		return userGroupVo;
	}
	public void setUserGroupVo(List<UserGroupVo> userGroupVo) {
		this.userGroupVo = userGroupVo;
	}
	
	
}
