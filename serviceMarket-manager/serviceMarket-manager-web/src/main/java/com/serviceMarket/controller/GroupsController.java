package com.serviceMarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serviceMarket.DTO.GroupDTO;
import com.serviceMarket.DTO.UserGroupDTO;
import com.serviceMarket.Vo.GroupsVo;
import com.serviceMarket.common.pojo.ServiceMarketResult;
import com.serviceMarket.service.GroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2017年12月2日 下午2:27:11 
* 类说明 团购的相关操作
*/

@Controller
@Api(tags="GroupsController",description="团购的相关操作")
public class GroupsController {
	
	@Autowired 
	private GroupService groupService;

	
	//发起团购
	@RequestMapping(value="/POST/Groups",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(httpMethod="POST",value = "发起团购",notes="DTO中所有属性都需要，使用json格式数据")
	public ServiceMarketResult addGroups(@ApiParam(name="GroupDTO",value="团购相关信息",required=true) @RequestBody GroupDTO GroupDTO){
		
		boolean flag=groupService.addGroups(GroupDTO);
		
		return ServiceMarketResult.ok(flag);
	}
	
	//删除团购
	@RequestMapping(value="/Delete/Groups/{groupId}")
	@ResponseBody
	@ApiOperation(httpMethod="DELETE",value = "取消团购",notes="需要groupId")
	public ServiceMarketResult addGroups(@PathVariable(value="groupId") Integer groupId){
		
		boolean flag=groupService.deleteGroups(groupId);
		return ServiceMarketResult.ok(flag);
	}
	
	//查看该商家的团购(正在进行中的)
	@RequestMapping(value="/Get/getGroupsByshopId/{shopId}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value = "查看该商家的团购(正在进行中的)",notes="需要shopId")
	public ServiceMarketResult getGroupsByshopId(@PathVariable(value="shopId") Integer shopId){
		
		List<GroupsVo> list=groupService.getGroupsByshopId(shopId,1);
		
		return ServiceMarketResult.ok(list);
	}
	
	//查看该商家的团购(即将开始的)
	@RequestMapping(value="/Get/getWillGroupsByshopId/{shopId}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value = "查看该商家的团购(即将开始的)",notes="需要shopId")
	public ServiceMarketResult getWillGroupsByshopId(@PathVariable(value="shopId") Integer shopId){
		
		List<GroupsVo> list=groupService.getGroupsByshopId(shopId,2);
		
		return ServiceMarketResult.ok(list);
	}
	
	
	//查看正在进行的团购（集市范围）
	@RequestMapping(value="/Get/getMarketGrouping/{markId}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value = "查看集市的团购(正在进行中的)",notes="需要markId")
	public ServiceMarketResult getMarketGrouping(@PathVariable(value="markId") Integer markId){
		
		List<GroupsVo> list=groupService.getGroupsByMarkId(markId,1);
		
		return ServiceMarketResult.ok(list);
	}
	
	//查看即将开始的团购（集市范围）
	@RequestMapping(value="/Get/getMarketWillGroups/{markId}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value = "查看集市的团购(即将开始的)",notes="需要markId")
	public ServiceMarketResult getMarketWillGroups(@PathVariable(value="markId") Integer markId){
		
		List<GroupsVo> list=groupService.getGroupsByMarkId(markId,2);
		
		return ServiceMarketResult.ok(list);
	}
	
	//加入团购
	@RequestMapping(value="/POST/joinGroups",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(httpMethod="POST",value = "用户加入团购",notes="除了id,UserGroupDTO中所有属性都需要，使用json格式数据")
	public ServiceMarketResult joinGroups(@ApiParam(name="UserGroupDTO",value="用户加入团购的相关信息",required=true) @RequestBody UserGroupDTO userGroupDTO){
		
		boolean flag=groupService.joinGroup(userGroupDTO);
		
		return ServiceMarketResult.ok(flag);
	}
	
	//查看我参与的团购
	@RequestMapping(value="/Get/getMyGroups/{userId}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value = "查看我参与的团购",notes="需要markId")
	public ServiceMarketResult getMyGroups(@PathVariable(value="userId") Integer userId){
		
		List<GroupsVo> list=groupService.myGroup(userId);
		
		return ServiceMarketResult.ok(list);
	}
	
	//修改我的团购资料
	@RequestMapping(value="/PUT/updateGroups")
	@ResponseBody
	@ApiOperation(httpMethod="PUT",value = "用户修改团购信息",notes="需要UserGroupDTO中id、address、phone、isDeliver(使用Y/N)，使用json格式数据")
	public ServiceMarketResult updateGroups(@ApiParam(name="UserGroupDTO",value="用户加入团购的相关信息",required=true) @RequestBody UserGroupDTO userGroupDTO){
		
		boolean flag=groupService.updateUserGroup(userGroupDTO);
		
		return ServiceMarketResult.ok(flag);
	}
	
	
	//用户退出团购
	@RequestMapping(value="/Delete/exitGroups/{userGroupId}")
	@ResponseBody
	@ApiOperation(httpMethod="DELETE",value = "用户退出团购",notes="需要UserGroup表的id，即userGroupId")
	public ServiceMarketResult exitGroups(@PathVariable(value="userGroupId") Integer userGroupId){
		
		boolean flag=groupService.exitGroup(userGroupId);
		return ServiceMarketResult.ok(flag);
	}
	
}
