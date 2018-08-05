package com.serviceMarket.Vo;

import java.util.Date;

import com.serviceMarket.pojo.State;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2018年2月2日 下午5:47:46 
* 类说明 
*/
public class HelperAcceptVo {
	private Integer id;

    private Integer helperId;

    private Integer helperTableId;

    private String deadTime;

    private String acceptTime;

    private String phone;

    private Integer state;
    
    private HelperTableVo helperTable;
    private StateVo stateVo;
    

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

	public HelperTableVo getHelperTable() {
		return helperTable;
	}

	public void setHelperTable(HelperTableVo helperTable) {
		this.helperTable = helperTable;
	}

	public StateVo getStateVo() {
		return stateVo;
	}

	public void setStateVo(StateVo stateVo) {
		this.stateVo = stateVo;
	}

}
