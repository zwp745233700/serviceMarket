package com.serviceMarket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.serviceMarket.DTO.HelpAccepetDTO;
import com.serviceMarket.DTO.HelperTableDTO;
import com.serviceMarket.DTO.StateDTO;
import com.serviceMarket.Vo.HelperAcceptVo;
import com.serviceMarket.Vo.HelperTableVo;
import com.serviceMarket.common.pojo.PageDateResult;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2018年2月2日 下午1:50:26 
* 类说明 代购操作的service层
*/
@Service
public interface HelperService {
	//注册成为代购者
	public boolean helperRegister(Integer userId);

	//用户发起代购
	public boolean addHelpRequest(HelperTableDTO helperTableDTO);
	
	//用户查看自己的代购请求
	public List<HelperTableVo> getMyHelperTable(Integer userId);
	
	//如果请求还没被接受，用户可以修改代购,即state==1
	public boolean updateHelperTable(HelperTableDTO helperTableDTO);
	
	//用户删除代购,只有代购还没被处理,即state==1才可以删除
	public boolean deleteHelperTable(Integer helpTableId);
	
	//查看需要代购的请求列表
	//public List<HelperTableVo> getAllHelperTable();
	
	public PageDateResult getAllHelperTable(int page,int rows);
	
	//代购者接受代购
	public boolean acceptRequest(HelpAccepetDTO helpAccepetDTO);
	
	//代购者查看自己接受的代购
	public List<HelperAcceptVo> getMyAccept(Integer helperId);
	
	//如果还没被用户确认，代购者可以取消已经接受的代购
	public boolean deleteAccept(Integer helperAcceptId);
	
	//用户再次确认时，同意被代购,同时创建state表
	public boolean agreeHelper(Integer helpTableId,Integer acceptId);
	
	//用户再次确实时，拒绝被代购
	public boolean refuseHelper(Integer acceptId);

	//代购物品已经送达,i=1表示用户操作，i=2表示代购者操作
	public boolean receiveGoods(int i,int stateId);
	
	//代购未送达,i=1表示用户操作，i=2表示代购者操作
	public boolean lossGoods(int i,int stateId);
	
	//用户评价
	public boolean evaluation(StateDTO stateDTO);
}
