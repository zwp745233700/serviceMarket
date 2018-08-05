package com.serviceMarket.service.Impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.serviceMarket.DTO.UserDTO;
import com.serviceMarket.Vo.UserInfoVo;
import com.serviceMarket.common.pojo.PictureResult;
import com.serviceMarket.common.pojo.ServiceMarketResult;
import com.serviceMarket.common.util.ExceptionUtil;
import com.serviceMarket.common.util.FtpUtil;
import com.serviceMarket.common.util.IDUtils;
import com.serviceMarket.common.util.SFTPUtil;
import com.serviceMarket.mapper.PermissionsMapper;
import com.serviceMarket.mapper.TokenMapper;
import com.serviceMarket.mapper.UserMapper;
import com.serviceMarket.pojo.Permissions;
import com.serviceMarket.pojo.PermissionsExample;
import com.serviceMarket.pojo.Token;
import com.serviceMarket.pojo.User;
import com.serviceMarket.pojo.UserExample;
import com.serviceMarket.pojo.UserExample.Criteria;
import com.serviceMarket.service.UserService;

/** * 
	  @author  Bling 
	  @E-mail: zlh8013gsf@126.com
  	  @date 创建时间：2017年11月27日 下午8:41:44 * 
	  @version 1.0 * 
	  @reason 
**/

@Service
public class UserServiceImpl implements UserService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PermissionsMapper permMapper;
	@Autowired
	private TokenMapper tokenMapper;
	
	@Override
	public UserInfoVo login(String count, String password) {
		//根据帐号密码查找用户信息
		UserInfoVo uiv = null;
		UserExample userExample = new UserExample();
		Criteria criteria  = userExample.createCriteria();
		criteria.andUserEqualTo(count);
		criteria.andPasswordEqualTo(password);
		List<User> list = userMapper.selectByExample(userExample);
		if(list.isEmpty()){
			return null;
		}else{
			User user = list.get(0);
			uiv = new UserInfoVo();
			uiv.setUserId(user.getId());
			uiv.setAddress(user.getAddress());
			uiv.setAge(user.getAge());
			uiv.setEmail(user.getEmail());
			uiv.setOther_link(user.getOtherLink());
			uiv.setPhone(user.getPhone());
			uiv.setPicture(user.getPicture());
			uiv.setQq(user.getQq());
			uiv.setRealName(user.getRealname());
			uiv.setWechat(user.getWechat());
			//检查权限
			Permissions perm = getPermissions(user.getId());
			if(perm==null){
				uiv.setShopper(false);
				uiv.setHelper(false);
			}else{
				if(perm.getIsShop().equals("Y")){
					uiv.setShopper(true);
				}else{
					uiv.setShopper(false);
				}
				if(perm.getIsHelper().equals("Y")){
					uiv.setHelper(true);
				}else{
					uiv.setHelper(false);
				}
				
			}
			return uiv;
		}
	}

	//查看用户权限
	private Permissions getPermissions(Integer id) {
		Permissions perm = null;
		PermissionsExample permEx = new PermissionsExample();
		com.serviceMarket.pojo.PermissionsExample.Criteria cri = permEx.createCriteria();
		cri.andUserIdEqualTo(id);
		List<Permissions> permList = permMapper.selectByExample(permEx);
		if(!permList.isEmpty()){
			perm = permList.get(0);
		}
		return perm;
	}

	@Override
	public boolean saveToken(Token token) {
		if(token!=null||token.getId()!=null){
			int result = tokenMapper.insert(token);
			if(result==1){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean register(UserDTO userDTO) {
		
		User user=new User();
		
		user.setUser(userDTO.getUser());
		user.setPassword(userDTO.getPassword());
		user.setPhone(userDTO.getPhone());
		user.setAddress(userDTO.getAddress());
		user.setPicture("");
		user.setAge(0);
		user.setEmail("");
		user.setOtherLink("");
		user.setPostcode("");
		user.setRealname("");
		user.setQq("");
		user.setWechat("");
		
		int result=userMapper.insert(user);
		
		//用户表添加成功
		if(result==1){
			//在permissions表中初始化权限
			Permissions permissions=new Permissions();
			permissions.setUserId(user.getId());
			permissions.setIsHelper("N");
			permissions.setIsShop("N");
			
			int result2=permMapper.insertSelective(permissions);
			
			if(result2==1)
			{
				return true;
			}
			return false;
		}
		return false;
	}

	//查询用户名是否存在
	public boolean isNameOk(String username){
		UserExample example=new UserExample();
		Criteria criteria=example.createCriteria();
		criteria.andUserEqualTo(username);
		
		List<User> list=userMapper.selectByExample(example);
		if(list.size()==0){
			return true;
		}
		return false;
	}
	
	//查询电话号码是否存在
	public boolean isPhoneOk(String phone){
		UserExample example=new UserExample();
		Criteria criteria=example.createCriteria();
		criteria.andPhoneEqualTo(phone);
		
		List<User> list=userMapper.selectByExample(example);
		
		if(list.size()==0){
			return true;
		}
		return false;
	}

	//查询用户详细信息
	@Override
	public UserInfoVo userDetail(int userid) {

		User user=userMapper.selectByPrimaryKey(userid);
		
		if(user!=null){
			UserInfoVo userInfo=new UserInfoVo();
			userInfo.setUserId(userid);
			userInfo.setAddress(user.getAddress());
			userInfo.setAge(user.getAge());
			userInfo.setEmail(user.getEmail());
			userInfo.setPhone(user.getPhone());
			userInfo.setPicture(user.getPicture());
			userInfo.setQq(user.getQq());
			userInfo.setRealName(user.getRealname());
			userInfo.setWechat(user.getWechat());
			userInfo.setOther_link(user.getOtherLink());
			
			//查询用户权限
			Permissions permissions=getPermissions(userid);
			if(permissions==null){
				userInfo.setShopper(false);
				userInfo.setHelper(false);
			}else{
				if(permissions.getIsShop().equals("Y")){
					userInfo.setShopper(true);
				}else{
					userInfo.setShopper(false);
				}
				if(permissions.getIsHelper().equals("Y")){
					userInfo.setHelper(true);
				}else{
					userInfo.setHelper(false);
				}
			}
			return userInfo;
		}
		
		return null;
	}
	
	@Override
	public boolean updUserInfo(UserDTO userDTO) {
		
		User user=new User();
		
		user.setId(userDTO.getId());
		user.setAge(userDTO.getAge());
		user.setRealname(userDTO.getRealname());
		user.setQq(userDTO.getQq());
		user.setEmail(userDTO.getEmail());
		user.setAddress(userDTO.getAddress());
		user.setOtherLink(userDTO.getOtherLink());
		user.setWechat(userDTO.getWechat());
		user.setPostcode(userDTO.getPostcode());
		
		int result=userMapper.updateByPrimaryKeySelective(user);
		
		if(result==1){
			return true;
		}
		return false;
	}

	@Override
	public ServiceMarketResult updPassword(UserDTO userDTO) {
		User user = new User();
		ServiceMarketResult result=null;
		
		//判断密码是否正确
		boolean flag=userDTO.getOldPassword().equals(userMapper.selectByPrimaryKey(userDTO.getId()).getPassword());
		
		if(flag){
			//旧密码正确
			user.setId(userDTO.getId());
			user.setPassword(userDTO.getNewPassword());
			
			int num=userMapper.updateByPrimaryKeySelective(user);
			
			if(num==1){
				result = new ServiceMarketResult("true");
			}else{
				result = new ServiceMarketResult("false");
			}
		}else{
			//旧密码错误
			return result = new ServiceMarketResult("原始密码错误");
		}
		return result;
	}	
	
	
	//上传文件
	@Override
	public PictureResult uploadPicture(MultipartFile uploadFile,Integer userId) {
		//判断上传图片是否为空
		if(null==uploadFile || uploadFile.isEmpty()){
			return PictureResult.error("上传图片为空");
		}
		
		//获取文件拓展名
		String originalFilename=uploadFile.getOriginalFilename();
		String ext=originalFilename.substring(originalFilename.lastIndexOf("."));
		//生成新的文件名
		String imageName=IDUtils.genImageName();
		
		//把图片上传到sftp服务器（图片服务器）
		String filePath="/user/"+userId+"/head";//图片目录路径
		try {
			//sftp连接方式上传文件
			SFTPUtil sftp = new SFTPUtil(FTP_USERNAME, FTP_PASSWORD, FTP_ADDRESS, FTP_PORT);  
			sftp.login();
			sftp.upload(FTP_BASE_PATH, filePath, imageName+ext, uploadFile.getInputStream());
			sftp.logout();  
			
			
			//ftp连接方式上传文件:由于服务器是sftp，所以不能使用ftp
			/*FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, 
					FTP_BASE_PATH, filePath, imageName + ext, uploadFile.getInputStream());*/
			
			} catch (Exception e) {
			return PictureResult.error(ExceptionUtil.getStackTrace(e));
		}
		
		//可以访问到图片的url
		String url=IMAGE_BASE_URL + filePath + "/" + imageName + ext;
		
		//将url保存到数据库中
		int result=updImageUrl(userId,url);
		
		if(result==1){
			return PictureResult.ok(url);
		}else{
			return PictureResult.error("false");
		}
	}
	
	
	//将头像url存到数据库中
	private int updImageUrl(Integer userId,String url){
		
		User user = new User();
		user.setId(userId);
		user.setPicture(url);
			
		int result=userMapper.updateByPrimaryKeySelective(user);
		return result;
	}


	@Override
	public boolean updPhone(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setPhone(userDTO.getPhone());
		
		int result=userMapper.updateByPrimaryKeySelective(user);

		if(result==1){
			return true;
		}else{
			return false;
		}
		
	}
}

