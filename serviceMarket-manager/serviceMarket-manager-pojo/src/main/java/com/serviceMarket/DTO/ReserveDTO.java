package com.serviceMarket.DTO;

import java.util.Date;
import java.util.List;

/**
 * *
 * 
 * @author Bling
 * @E-mail: zlh8013gsf@126.com
 * @date 创建时间：2017年11月24日 下午8:54:30 *
 * @version 1.0 *
 * @reason 一个dto对象，用于接受前端传来的预约对象的部分数据
 */
public class ReserveDTO {
	
	private Integer id;//预约单Id
	private Integer userId;
	private Integer shopId;
	
	private List<ReserveCartDTO> shopCart;
	
	private String des;

	private Date takeTime;

	private Integer price;

	private String phone;
	
	private String isPay;//是否支付定金
	
	private Integer pay;//定金

	
	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<ReserveCartDTO> getShopCart() {
		return shopCart;
	}

	public void setShopCart(List<ReserveCartDTO> shopCart) {
		this.shopCart = shopCart;
	}

	

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Date getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(Date takeTime) {
		this.takeTime = takeTime;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsPay() {
		return isPay;
	}

	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}

	public Integer getPay() {
		return pay;
	}

	public void setPay(Integer pay) {
		this.pay = pay;
	}
	
	
}
