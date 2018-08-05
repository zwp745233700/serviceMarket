package com.serviceMarket.Vo;

import java.util.Date;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2018年2月2日 下午7:20:29 
* 类说明 helperAccept表的Vo对象类
*/
public class AccepterVo {

	private Integer id;

    private Integer helperId;

    private Integer helperTableId;

    private String deadTime;

    private String acceptTime;

    private String phone;

    private Integer state;
    
    private String helperName;

    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHelperId() {
		return helperId;
	}

	public void setHelperId(Integer helperId) {
		this.helperId = helperId;
	}

	public Integer getHelperTableId() {
		return helperTableId;
	}

	public void setHelperTableId(Integer helperTableId) {
		this.helperTableId = helperTableId;
	}

	public String getDeadTime() {
		return deadTime;
	}

	public void setDeadTime(String deadTime) {
		this.deadTime = deadTime;
	}

	public String getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getHelperName() {
		return helperName;
	}

	public void setHelperName(String helperName) {
		this.helperName = helperName;
	}
    
}
