package com.serviceMarket.Vo;

import java.util.List;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2017年11月23日 下午8:25:03 
* 类说明 :展示商家列表的Vo
*/
public class ShowShopListVo {
	
	private String name;
	private String pic;
	private String type;
	private String descripe;
	private String satisfaction;
	private int shopId;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescripe() {
		return descripe;
	}
	public void setDescripe(String descripe) {
		this.descripe = descripe;
	}
	public String getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(String satisfaction) {
		this.satisfaction = satisfaction;
	}
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	
}
