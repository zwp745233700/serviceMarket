package com.serviceMarket.Vo; 
/** 
* @author 作者 张维鹏: 
* @version 创建时间：2017年11月27日 上午10:49:27 
* 类说明：显示商店商品信息的Vo
*/
public class GoodVo {
	private int goodId;//商品id
	private String name;//商品名称
	private int price;//商品价格
	private String img;//商品图片
	private int number;//商品剩余量
	
	public int getGoodId() {
		return goodId;
	}
	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
}
