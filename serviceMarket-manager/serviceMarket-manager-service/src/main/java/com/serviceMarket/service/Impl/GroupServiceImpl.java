package com.serviceMarket.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceMarket.DTO.GroupCartDTO;
import com.serviceMarket.DTO.GroupDTO;
import com.serviceMarket.DTO.UserGroupDTO;
import com.serviceMarket.Vo.GroupGoodVo;
import com.serviceMarket.Vo.GroupsVo;
import com.serviceMarket.Vo.ShowShopListVo;
import com.serviceMarket.Vo.UserGroupVo;
import com.serviceMarket.mapper.GroupDetailsMapper;
import com.serviceMarket.mapper.GroupsMapper;
import com.serviceMarket.mapper.ShopMapper;
import com.serviceMarket.mapper.UserGroupMapper;
import com.serviceMarket.mapper.UserMapper;
import com.serviceMarket.pojo.GroupDetails;
import com.serviceMarket.pojo.GroupDetailsExample;
import com.serviceMarket.pojo.Groups;
import com.serviceMarket.pojo.UserGroup;
import com.serviceMarket.pojo.GroupsExample.Criteria;
import com.serviceMarket.pojo.Shop;
import com.serviceMarket.pojo.User;
import com.serviceMarket.pojo.UserGroupExample;
import com.serviceMarket.service.GroupService;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2017年12月2日 下午3:10:04 
* 类说明 :团购的service层接口实现类
*/

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupsMapper groupsMapper;
	@Autowired
	private GroupDetailsMapper groupDetailsMapper;
	@Autowired
	private UserGroupMapper userGroupMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired ShopMapper shopMapper;
	
	
	@Override
	public boolean addGroups(GroupDTO groupDTO) {
		
		Groups group=new Groups();
		group.setEndTime(groupDTO.getEndTime());
		group.setGroupPrice(groupDTO.getGroupPrice());
		group.setLimitNum(groupDTO.getLimitNum());
		group.setNumber(groupDTO.getPeopleNum());
		group.setOriginalPrice(groupDTO.getOriginalPrice());
		group.setShopId(groupDTO.getShopId());
		group.setSurplus(groupDTO.getPeopleNum());//设置剩余量,初始值是满足团购的人数
		if(groupDTO.getStartTime()==null || "".equals(groupDTO.getStartTime())){
			group.setStartTime(new Date());
		}else{
			group.setStartTime(groupDTO.getStartTime());
		}
		
		groupsMapper.insertSelective(group);//保存商家发起的团购信息
		
		//插入数据失败，返回fasle
		if(group.getId()==null){
			return false;
		}else{//成功，执行保存团购物品详情操作：
			boolean result=addGroupDetail(group.getId(),groupDTO.getGroupCart());
			
			return result;
		}
	}

	
	//添加团购物品详情：
	private boolean addGroupDetail(Integer groupId, List<GroupCartDTO> list) {
		int flag=0;
		//循环赋值：
		for(GroupCartDTO groupCartDTO:list){
			GroupDetails groupDetails = new GroupDetails();
			groupDetails.setGoodsId(groupCartDTO.getGoodsId());
			groupDetails.setGroupId(groupId);
			groupDetails.setName(groupCartDTO.getName());
			groupDetails.setNum(groupCartDTO.getNum());
			
			int result=groupDetailsMapper.insertSelective(groupDetails);
			flag=flag+result;
		}
		if(flag==list.size()){
			return true;
		}else{
			return false;
		}
	}


	@Override
	public boolean deleteGroups(Integer groupId) {
		
		//删除团购详情表
		GroupDetailsExample gdExample=new GroupDetailsExample();
		com.serviceMarket.pojo.GroupDetailsExample.Criteria criteria1 = gdExample.createCriteria();
		criteria1.andGoodsIdEqualTo(groupId);
		groupDetailsMapper.deleteByExample(gdExample);
		
		//删除用户团购表
		UserGroupExample ugExample=new UserGroupExample();
		com.serviceMarket.pojo.UserGroupExample.Criteria criteria2=ugExample.createCriteria();
		criteria2.andGroupIdEqualTo(groupId);
		userGroupMapper.deleteByExample(ugExample);
		
		//删除团购表
		int result=groupsMapper.deleteByPrimaryKey(groupId);
		
		if(result==1){
			return true;
		}else{
			return false;
		}
		
	}


	@Override
	public List<GroupsVo> getGroupsByshopId(Integer shopId,int flag) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time=new Date();
		String nowTime=sdf.format(time);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nowTime",nowTime);
		map.put("shopId", shopId);
		
		List<Groups> groupsList=new ArrayList<Groups>();
		
		if(flag==1){
			groupsList=groupsMapper.getStartingGroup(map);
		}if(flag==2){
			groupsList=groupsMapper.getWillStartingGroup(map);
		}
		
		
		if(groupsList.isEmpty()){
			return null;
		}else{
			String startTime="";
			String endTime="";
			//将查询出来的结果封装到GroupsVo中
			List<GroupsVo> groupsVoList=new ArrayList<GroupsVo>();
			
			for(Groups group:groupsList){
				GroupsVo groupsVo=new GroupsVo();
				groupsVo.setId(group.getId());
				
				startTime=sdf.format(group.getStartTime());
				endTime=sdf.format(group.getEndTime());
				
				groupsVo.setStartTime(startTime);
				groupsVo.setEndTime(endTime);
				groupsVo.setGroupPrice(group.getGroupPrice());
				groupsVo.setLimitNum(group.getLimitNum());
				groupsVo.setOriginalPrice(group.getOriginalPrice());
				groupsVo.setPeopleNum(group.getNumber());
				groupsVo.setSurplus(group.getSurplus());
				
				
				//根据groupId查询商品,并设置商品信息
				List<GroupGoodVo> groupGoodVoList=getGroupGoodVoList(group.getId());
				//根据groupid查询userGroup表，并设置用户表信息
				List<UserGroupVo> userGroupVoList=getUserGroupVoList(group.getId());

				//设置商家信息，这里不需要设置商家信息
				
				groupsVo.setGoodVo(groupGoodVoList);
				groupsVo.setUserGroupVo(userGroupVoList);
				
				groupsVoList.add(groupsVo);
			}
			return groupsVoList;
		}
	}
	
	
	//根据groupId查询商品,并设置商品信息,返回List<GroupGoodVo>
	private List<GroupGoodVo> getGroupGoodVoList(Integer groupId){
		
		GroupDetailsExample gdExample=new GroupDetailsExample();
		com.serviceMarket.pojo.GroupDetailsExample.Criteria criteria=gdExample.createCriteria();
		criteria.andGroupIdEqualTo(groupId);
		List<GroupDetails> gdList=groupDetailsMapper.selectByExample(gdExample);
	
		List<GroupGoodVo> groupGoodVoList=new ArrayList<GroupGoodVo>();
		
		for(GroupDetails groupDetails:gdList){
			GroupGoodVo groupGoodVo=new GroupGoodVo();
			groupGoodVo.setId(groupDetails.getGoodsId());
			groupGoodVo.setName(groupDetails.getName());
			groupGoodVo.setNum(groupDetails.getNum());
			//添加到集合中
			groupGoodVoList.add(groupGoodVo);
		}
		return groupGoodVoList;
	}
	
	
	//根据groupid查询userGroup表，并设置用户表信息
	private List<UserGroupVo> getUserGroupVoList(Integer groupId){
		
		UserGroupExample ugExample = new UserGroupExample();
		com.serviceMarket.pojo.UserGroupExample.Criteria ugcriteria = ugExample.createCriteria();
		ugcriteria.andGroupIdEqualTo(groupId);
		List<UserGroup> ugList=userGroupMapper.selectByExample(ugExample);
		
		List<UserGroupVo> userGroupVoList=new ArrayList<UserGroupVo>();
		
		for(UserGroup userGroup:ugList){
			UserGroupVo userGroupVo=new UserGroupVo();
			userGroupVo.setId(userGroup.getId());
			userGroupVo.setAddress(userGroup.getAddress());
			userGroupVo.setGroupId(userGroup.getGroupId());
			userGroupVo.setIsDeliver(userGroup.getIsDeliver());
			userGroupVo.setNum(userGroup.getNum());
			userGroupVo.setPhone(userGroup.getPhone());
			userGroupVo.setUserId(userGroup.getUserId());
			
			User user=userMapper.selectByPrimaryKey(userGroup.getUserId());
			userGroupVo.setUsername(user.getUser());
			
			userGroupVoList.add(userGroupVo);
		}
		return userGroupVoList;
	}
	
	
	//根据shopId查询商店信息
	private ShowShopListVo getShopVo(Integer shopId){
		
		Shop shop=shopMapper.selectByPrimaryKey(shopId);
		
		ShowShopListVo shopVo=new ShowShopListVo();
		shopVo.setDescripe(shop.getDes());
		shopVo.setShopId(shopId);
		shopVo.setName(shop.getName());
		shopVo.setPic(shop.getLogo());
		shopVo.setSatisfaction(shop.getSatisfaction());
		shopVo.setType(shop.getType());
		
		return shopVo;
	}


	@Override
	public List<GroupsVo> getGroupsByMarkId(Integer markId, int flag) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time=new Date();
		String nowTime=sdf.format(time);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nowTime",nowTime);
		map.put("markId", markId);
		
		List<Groups> groupsList=new ArrayList<Groups>();
		
		if(flag==1){
			groupsList=groupsMapper.getMarketStartingGroup(map);
		}if(flag==2){
			groupsList=groupsMapper.getMarketWillStartGroup(map);
		}
		
		
		if(groupsList.isEmpty()){
			return null;
		}else{
			String startTime="";
			String endTime="";
			//将查询出来的结果封装到GroupsVo中
			List<GroupsVo> groupsVoList=new ArrayList<GroupsVo>();
			
			for(Groups group:groupsList){
				GroupsVo groupsVo=new GroupsVo();
				groupsVo.setId(group.getId());
				
				startTime=sdf.format(group.getStartTime());
				endTime=sdf.format(group.getEndTime());
				
				groupsVo.setStartTime(startTime);
				groupsVo.setEndTime(endTime);
				groupsVo.setGroupPrice(group.getGroupPrice());
				groupsVo.setLimitNum(group.getLimitNum());
				groupsVo.setOriginalPrice(group.getOriginalPrice());
				groupsVo.setPeopleNum(group.getNumber());
				groupsVo.setSurplus(group.getSurplus());
				
				
				//根据groupId查询商品,并设置商品信息
				List<GroupGoodVo> groupGoodVoList=getGroupGoodVoList(group.getId());
				//根据groupid查询userGroup表，并设置用户表信息
				List<UserGroupVo> userGroupVoList=getUserGroupVoList(group.getId());
				//根据shopId设置商家信息
				ShowShopListVo shopVo=getShopVo(group.getShopId());
				
				groupsVo.setGoodVo(groupGoodVoList);
				groupsVo.setUserGroupVo(userGroupVoList);
				groupsVo.setShopVo(shopVo);
				
				groupsVoList.add(groupsVo);
			}
			return groupsVoList;
		}
	}


	@Override
	public boolean joinGroup(UserGroupDTO userGroupDTO) {
		//应该添加判断团购是否已经成功的逻辑，为完成
		
		//group表中减少相应的数量
		Groups groups=groupsMapper.selectByPrimaryKey(userGroupDTO.getGroupId());
		int num=groups.getSurplus()-userGroupDTO.getNum();
		if(num<0){
			return false;
		}else{
			groups.setSurplus(num);
			int result2=groupsMapper.updateByPrimaryKeySelective(groups);
			
			//插入userGroup表的信息
			UserGroup userGroup=new UserGroup();
			userGroup.setAddress(userGroupDTO.getAddress());
			userGroup.setGroupId(userGroupDTO.getGroupId());
			userGroup.setIsDeliver(userGroupDTO.getIsDeliver());
			userGroup.setNum(userGroupDTO.getNum());
			userGroup.setUserId(userGroupDTO.getUserId());
			userGroup.setPhone(userGroupDTO.getPhone());
			
			int result1=userGroupMapper.insertSelective(userGroup);
			
			if(num==0){
				//判断团购是否成功的逻辑，未实现
				
				//通知用户的逻辑，付款逻辑，未实现
			}
			
			if(result1+result2==2){
				return true;
			}else{
				return false;
			}
		}
		
	}


	@Override
	public List<GroupsVo> myGroup(Integer userId) {
		//将查询出来的结果封装到GroupsVoList中
		List<GroupsVo> groupsVoList=new ArrayList<GroupsVo>();
		
		UserGroupExample example=new UserGroupExample();
		com.serviceMarket.pojo.UserGroupExample.Criteria criteria=example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<UserGroup> userGroupList=userGroupMapper.selectByExample(example);
		
		GroupsVo groupsVo=new GroupsVo();
		
		for(UserGroup userGroup:userGroupList){
			List<UserGroupVo> userGroupVoList=new ArrayList<UserGroupVo>();
			
			UserGroupVo userGroupVo=new UserGroupVo();
			userGroupVo.setId(userGroup.getId());
			userGroupVo.setAddress(userGroup.getAddress());
			userGroupVo.setGroupId(userGroup.getGroupId());
			userGroupVo.setIsDeliver(userGroup.getIsDeliver());
			userGroupVo.setNum(userGroup.getNum());
			userGroupVo.setPhone(userGroup.getPhone());
			userGroupVo.setUserId(userGroup.getUserId());
			
			User user=userMapper.selectByPrimaryKey(userGroup.getUserId());
			userGroupVo.setUsername(user.getUser());
			
			userGroupVoList.add(userGroupVo);
			
			groupsVo.setUserGroupVo(userGroupVoList);
			
			//查看group表
			Groups groups=groupsMapper.selectByPrimaryKey(userGroup.getGroupId());
		
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String endTime=sdf.format(groups.getEndTime());
			String startTime=sdf.format(groups.getStartTime());
			
			groupsVo.setEndTime(endTime);
			groupsVo.setStartTime(startTime);
			groupsVo.setGroupPrice(groups.getGroupPrice());
			groupsVo.setOriginalPrice(groups.getOriginalPrice());
			groupsVo.setId(groups.getId());
			groupsVo.setLimitNum(groups.getLimitNum());
			groupsVo.setPeopleNum(groups.getNumber());
			groupsVo.setSurplus(groups.getSurplus());
			
			//查看groupGoods
			List<GroupGoodVo> groupGoodVo=getGroupGoodVoList(groups.getId());
			groupsVo.setGoodVo(groupGoodVo);
			
			//查看shop表
			
			ShowShopListVo shopVo=getShopVo(groups.getShopId());
			groupsVo.setShopVo(shopVo);
			
			groupsVoList.add(groupsVo);
		}
		
		return groupsVoList;
	}


	@Override
	public boolean updateUserGroup(UserGroupDTO userGroupDTO) {
		
		UserGroup userGroup=new UserGroup();
		userGroup.setId(userGroupDTO.getId());
		userGroup.setIsDeliver(userGroupDTO.getIsDeliver());
		userGroup.setAddress(userGroupDTO.getAddress());
		userGroup.setPhone(userGroupDTO.getPhone());
		
		int result=userGroupMapper.updateByPrimaryKeySelective(userGroup);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}


	@Override
	public boolean exitGroup(Integer userGroupId) {
		UserGroup userGroup=userGroupMapper.selectByPrimaryKey(userGroupId);
		
		//修改Group表的剩余量
		Groups groups=groupsMapper.selectByPrimaryKey(userGroup.getGroupId());
		groups.setSurplus(groups.getSurplus()+userGroup.getNum());
		
		int result1=groupsMapper.updateByPrimaryKeySelective(groups);
		int result2=userGroupMapper.deleteByPrimaryKey(userGroupId);
		
		if(result1+result2==2){
			return true;
		}else{
			return false;
		}
	}
}
