package com.serviceMarket.Vo;

import java.util.List;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2018年2月2日 下午2:44:13 
* 类说明 helperTable表单的Vo对象
*/
public class HelperTableVo{

	private Integer id;

    private Integer userId;

    private String listing;

    private String address;

    private String arriveTime;

    private String phone;

    private Integer price;

    private String time;

    private Integer state;
	
	private String username;

	
	private StateVo stateVo;
	private List<AccepterVo> accepterVoList;

	
	public StateVo getStateVo() {
		return stateVo;
	}

	public void setStateVo(StateVo stateVo) {
		this.stateVo = stateVo;
	}

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

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<AccepterVo> getAccepterVoList() {
		return accepterVoList;
	}

	public void setAccepterVoList(List<AccepterVo> accepterVoList) {
		this.accepterVoList = accepterVoList;
	}
}
