package com.serviceMarket.Vo;

/** * 
@author  Bling 
@E-mail: zlh8013gsf@126.com
  @date 创建时间：2017年11月27日 下午8:28:26 * 
@version 1.0 * 
@reason 用户登录返回的信息
*/
public class UserInfoVo {
	private int userId;
	private String realName;
	private String phone;
	private int age;
	private String qq;
	private String email;
	private String wechat;
	private String address;
	private String other_link;
	private String picture;
	private boolean isShopper;
	private boolean isHelper;
	private String token;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOther_link() {
		return other_link;
	}
	public void setOther_link(String other_link) {
		this.other_link = other_link;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public boolean isShopper() {
		return isShopper;
	}
	public void setShopper(boolean isShopper) {
		this.isShopper = isShopper;
	}
	public boolean isHelper() {
		return isHelper;
	}
	public void setHelper(boolean isHelper) {
		this.isHelper = isHelper;
	}
	
}
