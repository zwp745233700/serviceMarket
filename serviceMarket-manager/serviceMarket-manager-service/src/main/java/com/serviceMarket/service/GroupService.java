package com.serviceMarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.serviceMarket.DTO.GroupDTO;
import com.serviceMarket.DTO.UserGroupDTO;
import com.serviceMarket.Vo.GroupsVo;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2017年12月2日 下午3:08:59 
* 类说明 :团购Service的接口
*/

@Service
public interface GroupService {
	
	//发起团购
	boolean addGroups(GroupDTO groupDTO);

	//删除团购
	boolean deleteGroups(Integer groupId);
	
	//查看该商家的团购;flag:1表示正在进行的，2表示即将开始的
	public List<GroupsVo> getGroupsByshopId(Integer shopId,int flag);
	
	//查看集市的团购，需要集市的id;flag:1表示正在进行的，2表示即将开始的
	public List<GroupsVo> getGroupsByMarkId(Integer markId,int flag);
	
	//加入团购
	public boolean joinGroup(UserGroupDTO userGroupDTO);
	
	//查看我参与的团购
	public List<GroupsVo> myGroup(Integer userId);
	
	//修改我的团购资料
	public boolean updateUserGroup(UserGroupDTO userGroupDTO);
	
	//退出团购
	public boolean exitGroup(Integer userGroupId);
	
}
