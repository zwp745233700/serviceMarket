package com.serviceMarket.DTO;

import com.serviceMarket.pojo.User;

import io.swagger.annotations.ApiModel;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2018年1月24日 下午8:29:02 
* 类说明 
*/

@ApiModel(value="UserDTO",description="注册信息")
public class UserDTO extends User{
	private String oldPassword;
	private String newPassword;
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}
