package com.serviceMarket.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2018年1月30日 下午2:58:23 
* 类说明 时间范围的DTO
*/
public class DateDTO {
	private Integer shopId;
	private Date startTime;
	private Date endTime;
	private Date month;
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getMonth() {
		return month;
	}
	public void setMonth(Date month) {
		this.month = month;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

}
