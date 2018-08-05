package com.serviceMarket.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.serviceMarket.DTO.UserDTO;
import com.serviceMarket.DTO.UserLoginDTO;
import com.serviceMarket.Vo.UserInfoVo;
import com.serviceMarket.common.pojo.PictureResult;
import com.serviceMarket.common.pojo.ServiceMarketResult;
import com.serviceMarket.common.util.SendMsgUtil;
import com.serviceMarket.pojo.Token;
import com.serviceMarket.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/** * 
	  @author  Bling 
	  @E-mail: zlh8013gsf@126.com
  	  @date 创建时间：2017年11月27日 下午9:18:04 * 
	  @version 1.0 * 
	  @reason 用户
**/
@Controller 
@Api(tags="UserController",description="用户相关的操作")
public class UserController {

	@Autowired
	private UserService userService;
	
	//登录
	@RequestMapping(value="/GET/userInfo",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(httpMethod="POST",value="用户登陆",notes="需要用户对象，使用json数据")
	public ServiceMarketResult login(@RequestBody UserLoginDTO user,HttpServletRequest request){
		ServiceMarketResult result = new ServiceMarketResult();
		UserInfoVo uiv = userService.login(user.getCount(), user.getPassword());
		if(uiv!=null){
			//生成一个uuid，并且生成一个uuid的toke对象，存入数据库和session中
			String uuid = UUID.randomUUID().toString();
			Token token = new Token();
			token.setId(uuid);
			token.setUserid(uiv.getUserId());
			token.setIshelper(uiv.isHelper());
			token.setIsshoper(uiv.isShopper());
			token.setExpertime(new Date());
			boolean flag = userService.saveToken(token);
			if(flag){
				//存到session域中
				HttpSession session = request.getSession();
				session.setAttribute("user", token);
				uiv.setToken(uuid);
				return result.ok(uiv);
			}
			
		}
		return result.ok();
	}
	
	//用户注册接口
	@RequestMapping(value="/POST/register",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(httpMethod="POST",value="用户注册接口",notes="json格式的用户数据")
	public ServiceMarketResult register(@ApiParam(name="UserDTO",value="只需要：user,password,address,phone") @RequestBody UserDTO userDTO){
		//注册逻辑
		boolean result=userService.register(userDTO);
		return ServiceMarketResult.ok(result);
	}
	
	//检查用户名是否存在接口
	@RequestMapping(value="GET/isNameOk/{username}",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="检查用户名是否可用",notes="需要用户名")
	public ServiceMarketResult isNameOk(@PathVariable(value="username")String username){
		
		boolean result=userService.isNameOk(username);
		return ServiceMarketResult.ok(result);
	}
	
	//检查电话号码是否存在接口
	@RequestMapping(value="GET/isPhoneOk/{phone}",method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="检查电话号码是否可用",notes="需要电话号码")
	public ServiceMarketResult isPhoneOk(@PathVariable(value="phone")String phone){

		boolean result=userService.isPhoneOk(phone);
		return ServiceMarketResult.ok(result);
	}
	
	//查看用户信息接口
	@RequestMapping(value="GET/userDetail/{userid}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="获取用户详细信息",notes="需要用户id")
	public ServiceMarketResult getUserDetail(@PathVariable(value="userid") Integer userid){
		UserInfoVo userInfoVo=userService.userDetail(userid);
		
		return ServiceMarketResult.ok(userInfoVo);
	}
	
	//用户修改信息接口
	@RequestMapping(value="PUT/updUserInfo")
	@ResponseBody
	@ApiOperation(httpMethod="PUT",value="修改用户信息",notes="需要id,年龄，地址，真实姓名，QQ，email,wechat,postcode,OtherLink")
	public ServiceMarketResult updUserInfo(@ApiParam @RequestBody UserDTO userDTO){
		
		boolean result=userService.updUserInfo(userDTO);
		return ServiceMarketResult.ok(result);
		
	}
	
	//用户修改头像接口
	@RequestMapping(method=RequestMethod.POST,value="/POST/updImage/{userId}",produces = {"application/json"},consumes="multipart/*",headers="content-type=multipart/form-data")
	@ResponseBody
	@ApiOperation(httpMethod="POST",value="修改头像接口",notes="需要头像和用户id")
	public PictureResult updImage(@ApiParam(value="上传的文件") MultipartFile uploadFile,final HttpServletRequest request,@PathVariable(value="userId") Integer userId){
		
		PictureResult result=userService.uploadPicture(uploadFile,userId);
		
		return result;
	}
	
	//短信验证接口
	@RequestMapping(value="/GET/authentication/{phone}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="用户注册时，需要绑定唯一的手机：短信验证码,调用第三方接口:sms_webchinese",
	notes="成功时返回一个6位数验证码，失败时返回false")
	public ServiceMarketResult authentication(@PathVariable(value="phone")String phone,HttpServletRequest request){
		
		HashMap<String, String> map = SendMsgUtil.getMessageStatus(phone); //应用发送短信接口
			
		String result = map.get("result");//获取到result值
		if (result.trim().equals("1")) {//如果为1，表示成功发送
			
			String code = map.get("code");//获取发送的验证码内容
			HttpSession session = request.getSession(); //设置session
			session.setAttribute("code", code);//将短信验证码放到session中保存
			session.setMaxInactiveInterval(60 * 5);//保存时间 暂时设定为5分钟
			
			System.out.println(code);
			return ServiceMarketResult.ok(code);
			
		} else {
			//短信发送失败
			return ServiceMarketResult.ok("false");
		}
		
	}
	
	//修改绑定手机接口
	@RequestMapping(value="/PUT/updPhone")
	@ResponseBody
	@ApiOperation(httpMethod="PUT",value="更改绑定号码",notes="需要更改后的手机号码,用户id")
	public ServiceMarketResult updPhone(@RequestBody UserDTO userDTO){
		
		try{
			boolean result=userService.updPhone(userDTO);
			return ServiceMarketResult.ok(result);
		}catch(Exception e){
			return ServiceMarketResult.ok("false");
		}
	}
	
	//修改密码接口
	@RequestMapping(value="/PUT/updPassword")
	@ResponseBody
	@ApiOperation(httpMethod="PUT",value="更改登陆密码",notes="需要oldpassword,newpassword,用户id")
	public ServiceMarketResult updPassword(@RequestBody UserDTO userDTO){
		
		ServiceMarketResult result=userService.updPassword(userDTO);
		
		return result;
	}
	
	//退出登录接口
	@RequestMapping(value="/Delete/logout")
	@ResponseBody
	@ApiOperation(httpMethod="DELETE",value="安全退出,清除session中的数据",notes="不需要数据")
	public void logout(HttpSession session) throws Exception{
		session.invalidate();
	}
	
	//根据用户名模糊查询用户
	//根据用户名查询用户
	//根据手机号查询用户
	
}

