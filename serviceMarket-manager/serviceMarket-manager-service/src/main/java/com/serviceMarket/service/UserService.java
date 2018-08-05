package com.serviceMarket.service;
/** * 
	  @author  Bling 
	  @E-mail: zlh8013gsf@126.com
  	  @date 创建时间：2017年11月27日 下午8:37:50 * 
	  @version 1.0 * 
	  @reason 
**/

import org.springframework.web.multipart.MultipartFile;

import com.serviceMarket.DTO.UserDTO;
import com.serviceMarket.Vo.UserInfoVo;
import com.serviceMarket.common.pojo.PictureResult;
import com.serviceMarket.common.pojo.ServiceMarketResult;
import com.serviceMarket.pojo.Token;

public interface UserService {

	/**
	 * 用户登录，成功后返回用户信息，否则返回错误信息
	 * @param count   用户帐号
	 * @param password   用户密码
	 * @return
	 */
	public UserInfoVo login(String count,String password);
	
	public boolean saveToken(Token token);
	
	//注册
	public boolean register(UserDTO userDTO);
	
	//用户名是否可用
	public boolean isNameOk(String username);
	
	//电话号码是否可用
	public boolean isPhoneOk(String phone);
	
	//查看用户详情
	public UserInfoVo userDetail(int userid);

	//修改用户信息
	public boolean updUserInfo(UserDTO userDTO);
	
	//修改密码
	public ServiceMarketResult updPassword(UserDTO userDTO);
	
	//修改用户图片
	public PictureResult uploadPicture(MultipartFile uploadFile, Integer userId);
	
	//更改绑定手机
	public boolean updPhone(UserDTO userDTO);
}

