package com.serviceMarket.DTO;

import java.util.Date;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2018年2月2日 下午2:18:26 
* 类说明 
*/
public class HelperTableDTO {
	
	private Integer id;//HelperTable的id
	private Integer userId;//发起请求的人的id
	private String listing;//代购的内容
	private String address;//地址
	private Date arriveTime;//送达时间
	private String phone;
	private Date time;//发起时间
	private int price;//金钱
	private int state;//订单状态
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getListing() {
		return listing;
	}
	public void setListing(String listing) {
		this.listing = listing;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
