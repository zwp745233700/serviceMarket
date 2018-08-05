package com.serviceMarket.Vo;
/** * 
@author  Bling 
@E-mail: zlh8013gsf@126.com
  @date 创建时间：2017年11月24日 下午9:09:57 * 
@version 1.0 * 
@reason 预约列表vo中的清单项 购物车vo
*/
public class ReserveCartVo {
	private int goodsId;
	private int number;
	private int price;
	private String name;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	
	
}
