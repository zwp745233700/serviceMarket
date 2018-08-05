package com.serviceMarket.DTO;


import com.serviceMarket.pojo.Goods;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2018年1月28日 下午12:04:44 
* 类说明 接收页面传过来的Good参数
*/
public class GoodsDTO extends Goods {
	private Integer markId;//集市id

	public Integer getMarkId() {
		return markId;
	}

	public void setMarkId(Integer markId) {
		this.markId = markId;
	}
	
}
