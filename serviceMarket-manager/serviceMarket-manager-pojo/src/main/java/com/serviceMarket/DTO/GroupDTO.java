package com.serviceMarket.DTO;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2017年12月2日 下午2:17:27 
* 类说明 :接收发起团购的信息的DTO
*/


@ApiModel(value="GroupDTO",description="团购详情")
public class GroupDTO {
	private int shopId;//商家id
	private List<GroupCartDTO> groupCart;//团购内容
	private int peopleNum;//满足人数
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private int originalPrice;//原价
	private int groupPrice;//团购价
	private int limitNum;//每人限买几份

	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public List<GroupCartDTO> getGroupCart() {
		return groupCart;
	}
	public void setGroupCart(List<GroupCartDTO> groupCart) {
		this.groupCart = groupCart;
	}
	public int getPeopleNum() {
		return peopleNum;
	}
	public void setPeopleNum(int peopleNum) {
		this.peopleNum = peopleNum;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(int originalPrice) {
		this.originalPrice = originalPrice;
	}
	public int getGroupPrice() {
		return groupPrice;
	}
	public void setGroupPrice(int groupPrice) {
		this.groupPrice = groupPrice;
	}
	public int getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
}
