package com.serviceMarket.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serviceMarket.DTO.DateDTO;
import com.serviceMarket.DTO.ReserveDTO;
import com.serviceMarket.Vo.ReserveListVO;
import com.serviceMarket.common.pojo.ServiceMarketResult;
import com.serviceMarket.service.ReserveService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** * 
@author  Bling 
@E-mail: zlh8013gsf@126.com
  @date 创建时间：2017年11月26日 上午9:49:46 * 
@version 1.0 * 
@reason  预约controller 
*/

@Controller
@Api(tags="reserveController",description="预约订单的相关操作")
public class reserveController {
	
	@Autowired
	private ReserveService reserveService;
	/**
	 * 新增一个预约订单
	 * @param reserveDTO
	 * @return
	 */
	@RequestMapping(value="/POST/reserve",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(httpMethod="POST",value="添加预约",notes="除了id,isPay,pay,price,其他都需要,json格式")
	public ServiceMarketResult addReserve(@RequestBody ReserveDTO reserveDTO){
		
		boolean flag = reserveService.saveReserve(reserveDTO);
		return ServiceMarketResult.ok(flag);
	
	}
	/**
	 * 查询一家商店的预约订单
	 * @param shopId
	 * @return
	 */
	@RequestMapping(value="/GET/reserve/{shopId}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="查看预约订单",notes="需要商家id")
	public ServiceMarketResult getReserve(@PathVariable int shopId){
		List<ReserveListVO> list = reserveService.reserveList(shopId);
		return ServiceMarketResult.ok(list);
	}
	
	
	//商家接收预约，并填写相应的定金
	@RequestMapping(value="/PATCH/acceptReserve/{userId}")
	@ResponseBody
	@ApiOperation(httpMethod="PATCH",value="接受预约并填写定金",notes="需要订单id,定金pay和商家对应的user表的id(用于权限验证)")
	public ServiceMarketResult patchReserveByReserveId(@PathVariable(value="userId") Integer userId,@RequestBody ReserveDTO reserveDTO){
		
		boolean flag = reserveService.acceptReserve(reserveDTO);
		//return ServiceMarketResult.ok(flag);
		//boolean flag=reserveService.changeReserveState(reserveId, 2);
		return ServiceMarketResult.ok(flag);
	}
	
	//取消预约
	@RequestMapping(value="/DELETE/deleteReserve/{userId}/{reserveId}")
	@ResponseBody
	@ApiOperation(httpMethod="DELETE",value="取消预约",notes="需要订单id和用户id(用于权限验证)")
	public ServiceMarketResult deleteReserve(@PathVariable(value="reserveId") int reserveId,@PathVariable(value="userId")Integer userId){
		boolean flag=reserveService.changeReserveState(reserveId, 4);
		return ServiceMarketResult.ok(flag);
	}
	
	//拒绝预约
	@RequestMapping(value="/PATCH/refuseReserve/{userId}/{reserveId}")
	@ResponseBody
	@ApiOperation(httpMethod="PATCH",value="拒绝预约",notes="需要订单id和商家对应的user表的id(用于权限验证)")
	public ServiceMarketResult refuseReserve(@PathVariable(value="reserveId") int reserveId,@PathVariable(value="userId")Integer userId){
		boolean flag=reserveService.changeReserveState(reserveId, 3);
		return ServiceMarketResult.ok(flag);
	}
	
	//用户再次确认并支付定金
	@RequestMapping(value="/PATCH/confirmReserve/{userId}/{reserveId}")
	@ResponseBody
	@ApiOperation(httpMethod="PATCH",value="用户再次确认预约并支付定金(即成功预约)",notes="需要订单id和userId(用于权限验证)")
	public ServiceMarketResult confirmReserve(@PathVariable(value="reserveId") int reserveId,@PathVariable(value="userId")Integer userId){
		//身份验证
		//支付逻辑
		boolean flag=reserveService.changeReserveState(reserveId, 5);
		return ServiceMarketResult.ok(flag);
	}
	
	//用户修改预约信息
	@RequestMapping(value="PUT/updateReserve")
	@ResponseBody
	@ApiOperation(httpMethod="PUT",value="用户修改预约信息",
	notes="商家接单后不能修改,即state==1是才可以修改，需要id、phone、des、takeTime")
	public ServiceMarketResult updateReserve(@RequestBody ReserveDTO reserveDTO){
		boolean flag=reserveService.updateReserve(reserveDTO);
		return ServiceMarketResult.ok(flag);
	}
	
	//按预约状态查看预约单(未完成)
	@RequestMapping(value="GET/ReserveListByState/{shopId}/{state}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="根据预约单的状态查看预约",
	notes="需要shopId,state:1用户预约,2商家确认并填写定金,3商家拒接,5用户再次确认并支付定金(即预约成功)")
	public ServiceMarketResult getReserveListByState(@PathVariable(value="shopId") Integer shopId,@PathVariable(value="state") Integer state){
		List<ReserveListVO> list=reserveService.getReserveListByState(shopId, state);
		
		return ServiceMarketResult.ok(list);
	}
	
	//查看指定时间段取货的预约单
	@RequestMapping(value="GET/getReserveLisByDate")
	@ResponseBody
	@ApiOperation(httpMethod="POST",value="查询指定时间段的要取货的预约单",
	notes="需要shopId,startTime和endTime,时间使用dateTime形式,json格式")
	public ServiceMarketResult getReserveLisByDate(@RequestBody DateDTO dateDTO){
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime=sdf.format(dateDTO.getStartTime());
		String endTime=sdf.format(dateDTO.getEndTime());
		Integer shopId=dateDTO.getShopId();
		
		List<ReserveListVO> list=reserveService.getReserveListByDate(shopId, startTime, endTime);
		return ServiceMarketResult.ok(list);
	}
	
	//统计本月预约量
	@RequestMapping(value="GET/getReserveNum")
	@ResponseBody
	@ApiOperation(httpMethod="POST",value="查询本月的预约量",
	notes="需要shopId,当前时间month,时间使用dateTime形式,json格式")
	public ServiceMarketResult getReserveNum(@RequestBody DateDTO dateDTO){
		
		int num=reserveService.getReserveNum(dateDTO);
		return ServiceMarketResult.ok(num);
	}
	
	//查看我的预约单
	@RequestMapping(value="GET/getMyReserve/{userId}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="查看我的预约单",notes="需要userId")
	public ServiceMarketResult getMyReserve(@PathVariable Integer userId){
		List<ReserveListVO> list=reserveService.getMyReserve(userId);
		return ServiceMarketResult.ok(list);
	}
}
