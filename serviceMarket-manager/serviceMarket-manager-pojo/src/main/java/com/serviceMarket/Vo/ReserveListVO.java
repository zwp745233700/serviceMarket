package com.serviceMarket.Vo;

import java.util.Date;
import java.util.List;

/** * 
@author  Bling 
@E-mail: zlh8013gsf@126.com
  @date 创建时间：2017年11月24日 下午9:07:28 * 
@version 1.0 * 
@reason 商家查看预约列表的vo
*/
public class ReserveListVO {
	private int reserveId;
	private int userId;
	private String userName;
	private List<ReserveCartVo> reserveCart;
	private int price;
	private String takeTime;
	private String phone;
	private String desc;
	private String reserveTime;
	private String isPay;
	private int pay;
	

	public int getReserveId() {
		return reserveId;
	}
	public void setReserveId(int reserveId) {
		this.reserveId = reserveId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<ReserveCartVo> getReserveCart() {
		return reserveCart;
	}
	public void setReserveCart(List<ReserveCartVo> reserveCart) {
		this.reserveCart = reserveCart;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getIsPay() {
		return isPay;
	}
	public void setIsPay(String isPay) {
		this.isPay = isPay;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	public String getTakeTime() {
		return takeTime;
	}
	public void setTakeTime(String takeTime) {
		this.takeTime = takeTime;
	}
	public String getReserveTime() {
		return reserveTime;
	}
	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}
	
}
