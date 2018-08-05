package com.serviceMarket.Vo; 
/** 
* @author 作者 张维鹏: 
* @version 创建时间：2017年11月30日 上午12:57:16 
* 类说明 :订单列表Vo的购物车内商品的Vo
*/
public class OrdersCartVo {
	private int goodsId;//商品Id
	private int number;//购买数量
	private int price;//价格
	private String name;//商品名称
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
