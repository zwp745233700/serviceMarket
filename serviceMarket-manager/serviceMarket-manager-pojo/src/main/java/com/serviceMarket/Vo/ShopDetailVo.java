package com.serviceMarket.Vo;

import java.util.List;

import com.serviceMarket.pojo.Shop;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2017年11月27日 上午10:40:43 
* 类说明 显示商家详情的Vo 包括商家的基本信息以及商家商品的基本信息
*/
public class ShopDetailVo extends Shop{
	
	private List<GoodVo> goodList;//商品集合

	public List<GoodVo> getGoodList() {
		return goodList;
	}
	public void setGoodList(List<GoodVo> goodList) {
		this.goodList = goodList;
	}

}
