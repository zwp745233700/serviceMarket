package com.serviceMarket.Vo;

import java.util.Date;

import com.serviceMarket.pojo.State;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2018年2月2日 下午2:45:26 
* 类说明 请求者/代购者之间的详情表
*/
public class StateVo{
	
    private Integer id;

    private Integer helperTableId;

    private Integer helperId;

    private Integer userAcceptState;

    private Integer arriveState;

    private String createTime;

    private String arriveTime;

    private String appraise;
    
	//private String helperName;

/*	public String getHelperName() {
		return helperName;
	}

	public void setHelperName(String helperName) {
		this.helperName = helperName;
	}*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHelperTableId() {
		return helperTableId;
	}

	public void setHelperTableId(Integer helperTableId) {
		this.helperTableId = helperTableId;
	}

	public Integer getHelperId() {
		return helperId;
	}

	public void setHelperId(Integer helperId) {
		this.helperId = helperId;
	}

	public Integer getUserAcceptState() {
		return userAcceptState;
	}

	public void setUserAcceptState(Integer userAcceptState) {
		this.userAcceptState = userAcceptState;
	}

	public Integer getArriveState() {
		return arriveState;
	}

	public void setArriveState(Integer arriveState) {
		this.arriveState = arriveState;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	public String getAppraise() {
		return appraise;
	}

	public void setAppraise(String appraise) {
		this.appraise = appraise;
	}
	

}
