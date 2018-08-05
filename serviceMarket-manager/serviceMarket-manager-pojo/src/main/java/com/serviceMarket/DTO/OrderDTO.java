package com.serviceMarket.DTO;

import java.util.List;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2017年11月27日 下午1:15:54 
* 类说明 :一个dto对象，用于接受前端传来的订购对象的部分数据
*/
public class OrderDTO {
	private Integer id;//订单Id
	private Integer userId;//用户Id
	private Integer shopId;//商店Id
	private List<OrderCartDTO> shopCart;//订购商品的列表
	
	private Integer price;//总价格
	private String phone;//联系电话
	private String address;//地址
	private String remark;//订单备注
	private String deliver;//是否送货
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public List<OrderCartDTO> getShopCart() {
		return shopCart;
	}
	public void setShopCart(List<OrderCartDTO> shopCart) {
		this.shopCart = shopCart;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDeliver() {
		return deliver;
	}
	public void setDeliver(String deliver) {
		this.deliver = deliver;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
