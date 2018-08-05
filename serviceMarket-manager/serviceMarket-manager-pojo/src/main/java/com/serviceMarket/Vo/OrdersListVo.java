package com.serviceMarket.Vo;

import java.util.List;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2017年11月30日 上午12:55:59 
* 类说明 :商家查看订单列表的VO
*/
public class OrdersListVo {
	private int orderId;//订单id
	private int userId;//用户id
	private int shopId;//商店id
	private String username;//用户名
	private List<OrdersCartVo> goodsList;//购物车
	private Integer price;//总价格
	private String deliver;//是否送货
	private String address;//收货地址
	private String phone;//联系电话
	private String remark;//备注
	private String createTime;//下单时间
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getDeliver() {
		return deliver;
	}
	public void setDeliver(String deliver) {
		this.deliver = deliver;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<OrdersCartVo> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<OrdersCartVo> goodsList) {
		this.goodsList = goodsList;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
