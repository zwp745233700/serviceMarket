package com.serviceMarket.DTO; 
/** 
* @author 作者 张维鹏: 
* @version 创建时间：2017年12月2日 下午2:15:11 
* 类说明:团购车商品信息列表列表：
*/
public class GroupCartDTO {
	private int goodsId;//商品id
	private String name;//商品名字
	private int num;//商品的数量

	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
