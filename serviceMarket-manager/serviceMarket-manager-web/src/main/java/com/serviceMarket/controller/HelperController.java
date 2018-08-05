package com.serviceMarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serviceMarket.DTO.HelpAccepetDTO;
import com.serviceMarket.DTO.HelperTableDTO;
import com.serviceMarket.DTO.StateDTO;
import com.serviceMarket.Vo.HelperAcceptVo;
import com.serviceMarket.Vo.HelperTableVo;
import com.serviceMarket.common.pojo.PageDateResult;
import com.serviceMarket.common.pojo.ServiceMarketResult;
import com.serviceMarket.service.HelperService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2018年1月29日 下午5:03:16 
* 类说明 代购者操作的相关Controller类
*/

@Controller
@Api(tags="HelperController",description="代购的相关操作")
public class HelperController {
	
	@Autowired
	private HelperService helperService;
	
	//注册成为代购者
	@RequestMapping(value="/PUT/helperRegister/{userId}")
	@ResponseBody
	@ApiOperation(httpMethod="PUT",value="用户注册成为代购者",notes="需要userId")
	public ServiceMarketResult helperRegister(@PathVariable Integer userId){
		boolean flag=helperService.helperRegister(userId);
		
		return ServiceMarketResult.ok(flag);
	}
	
	//用户发起代购
	@RequestMapping(value="/POST/addHelpRequest")
	@ResponseBody
	@ApiOperation(httpMethod="POST",value="用户发起代购请求",notes="HelperTableDTO,除了id,time,state，其他都要")
	public ServiceMarketResult addHelpRequest(@RequestBody HelperTableDTO helperTableDTO){
		boolean flag=helperService.addHelpRequest(helperTableDTO);
		
		return ServiceMarketResult.ok(flag);
	}
	
	//用户查看自己的代购请求
	@RequestMapping(value="/GET/getMyHelperTable/{userId}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="用户查看自己的代购请求",
	notes="需要用户Id,返回的accepterVoList表示所有接受请求的代购者集合 ;stateVo表示‘请求者/代购者之间的详情表’。")
	public ServiceMarketResult getMyHelperTable(@PathVariable(value="userId") Integer userId){
		List<HelperTableVo> list=helperService.getMyHelperTable(userId);
		
		return ServiceMarketResult.ok(list);
	}
	
	//如果请求还没被接受，用户可以修改代购
	@RequestMapping(value="/PUT/updateHelperTable")
	@ResponseBody
	@ApiOperation(httpMethod="PUT",value="如果请求还没被接受(即state==1)，用户可以修改代购内容",
	notes="HelperTableDTO,除了time,其他都要,json形式")
	public ServiceMarketResult updateHelperTable(@RequestBody HelperTableDTO helperTableDTO){
		
		boolean flag=helperService.updateHelperTable(helperTableDTO);
		return ServiceMarketResult.ok(flag);
	}
	
	//用户删除代购
	@RequestMapping(value="/DELETE/deleteHelperTable/{helpTableId}")
	@ResponseBody
	@ApiOperation(httpMethod="DELETE",value="用户删除代购,只有代购还没被用户再次确认时,即state==1、2时,才可以删除",
	notes="需要代购表的Id,即helpTableId")
	public ServiceMarketResult deleteHelperTable(@PathVariable Integer helpTableId){
		
		boolean flag=helperService.deleteHelperTable(helpTableId);
		return ServiceMarketResult.ok(flag);
	}
	
	
	//查看需要代购的请求列表
/*	@RequestMapping(value="/GET/getAllHelperTable")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="查看需要代购的请求列表",notes="不需要参数")
	public ServiceMarketResult getAllHelperTable(){
		List<HelperTableVo> list=helperService.getAllHelperTable();
		return ServiceMarketResult.ok(list);
	}*/
	
	@RequestMapping(value="/GET/getAllHelperTable")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="查看需要代购的请求列表",notes="需要参数：页数page，默认1 和每页数量rows，默认20")
	public PageDateResult getAllHelperTable(@RequestParam(value="page",defaultValue="1")Integer page,@RequestParam(value="rows",defaultValue="20")Integer rows){
		PageDateResult result=helperService.getAllHelperTable(page,rows);
		return result;
	}
	
	//代购者接受代购
	@RequestMapping(value="/POST/acceptRequest")
	@ResponseBody
	@ApiOperation(httpMethod="POST",value="代购者接受代购",notes="除了id,acceptTime,state,")
	public ServiceMarketResult acceptRequest(@RequestBody HelpAccepetDTO helpAccepetDTO){
		boolean flag=helperService.acceptRequest(helpAccepetDTO);
		
		return ServiceMarketResult.ok(flag);
	}
	
	//代购者查看自己接受的代购
	@RequestMapping(value="/GET/getMyAccept/{helperId}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="代购者查看自己接受的代购",notes="需要helperId,即代购者的userId")
	public ServiceMarketResult getMyAccept(@PathVariable(value="helperId") Integer helperId){
		List<HelperAcceptVo> list=helperService.getMyAccept(helperId);
		
		return ServiceMarketResult.ok(list);
	}

	//如果还没被用户确认，代购者可以取消已经接受的代购
	@RequestMapping(value="/DELETE/deleteAccept/{helperAcceptId}")
	@ResponseBody
	@ApiOperation(httpMethod="DELETE",value="如果还没被用户确认(即state!=2),代购者可以取消已经接受的代购",notes="需要代购接受表的id,即helperAcceptId")
	public ServiceMarketResult deleteAccept(@PathVariable(value="helperAcceptId") Integer helperAcceptId){
		boolean flag=helperService.deleteAccept(helperAcceptId);
		
		return ServiceMarketResult.ok(flag);
	}
	
	
	//用户再次确认时，同意被代购
	@RequestMapping(value="/PUT/agreeHelper/{helpTableId}/{acceptId}")
	@ResponseBody
	@ApiOperation(httpMethod="PUT",value="同意代购者帮自己代购",
	notes="需要代购接受表的id,即helperAcceptId，以及helper_table表的id")
	public ServiceMarketResult agreeHelper(@PathVariable Integer helpTableId,@PathVariable Integer acceptId){
		boolean flag=helperService.agreeHelper(helpTableId, acceptId);
		
		return ServiceMarketResult.ok(flag);
	}
	
	
	//用户再次确认时，拒绝被代购
	@RequestMapping(value="/PUT/refuseHelper/{acceptId}")
	@ResponseBody
	@ApiOperation(httpMethod="PUT",value="拒绝代购者帮自己代购",
	notes="需要代购接受表的id,即acceptId")
	public ServiceMarketResult refuseHelper(@PathVariable Integer acceptId){
		boolean flag=helperService.refuseHelper(acceptId);
		
		return ServiceMarketResult.ok(flag);
	}
	
	
	//代购物品已经送达(用户操作)
	@RequestMapping(value="/PUT/receiveGoods/{stateId}")
	@ResponseBody
	@ApiOperation(httpMethod="PUT",value="代购物品已经送达(用户操作)",
	notes="需要代购状态表state表的id,即stateId")
	public ServiceMarketResult receiveGoods(@PathVariable Integer stateId){
		boolean flag=helperService.receiveGoods(1, stateId);
		
		return ServiceMarketResult.ok(flag);
	}
	
	//代购物品已经送达(代购者操作)
	@RequestMapping(value="/PUT/deliverySuccess/{stateId}")
	@ResponseBody
	@ApiOperation(httpMethod="PUT",value="代购物品已经送达(代购者操作)",
	notes="需要代购状态表state表的id,即stateId")
	public ServiceMarketResult deliverySuccess(@PathVariable Integer stateId){
		boolean flag=helperService.receiveGoods(2, stateId);
		
		return ServiceMarketResult.ok(flag);
	}
	
	
	//代购未送达(用户操作)
	@RequestMapping(value="/PUT/lossGoods/{stateId}")
	@ResponseBody
	@ApiOperation(httpMethod="PUT",value="代购物品未送达(用户操作)",
	notes="需要代购状态表state表的id,即stateId")
	public ServiceMarketResult lossGoods(@PathVariable Integer stateId){
		boolean flag=helperService.lossGoods(1, stateId);
		
		return ServiceMarketResult.ok(flag);
	}
	
	//代购未送达(代购者操作)
	@RequestMapping(value="/PUT/deliveryFail/{stateId}")
	@ResponseBody
	@ApiOperation(httpMethod="PUT",value="代购物品未送达(代购者操作)",
	notes="需要代购状态表state表的id,即stateId")
	public ServiceMarketResult deliveryFail(@PathVariable Integer stateId){
		boolean flag=helperService.lossGoods(2, stateId);
		
		return ServiceMarketResult.ok(flag);
	}
	
	//用户评价
	@RequestMapping(value="/PUT/evaluation")
	@ResponseBody
	@ApiOperation(httpMethod="PUT",value="用户评价",
	notes="需要StateDTO的id,userAcceptState,appraise")
	public ServiceMarketResult evaluation(@RequestBody StateDTO stateDTO){
		
		boolean flag=helperService.evaluation(stateDTO);
		
		return ServiceMarketResult.ok(flag);
	}
}
