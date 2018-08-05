package com.serviceMarket.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serviceMarket.DTO.DateDTO;
import com.serviceMarket.DTO.OrderDTO;
import com.serviceMarket.Vo.OrdersListVo;
import com.serviceMarket.common.pojo.ServiceMarketResult;
import com.serviceMarket.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2017年11月27日 下午12:48:27 
* 类说明 :关于商家订单的Controller
*/

@Controller
@Api(tags="OrderController",description="订单相关的操作")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	//增加订购
	@RequestMapping(value="/POST/order",method=RequestMethod.POST)
	@ResponseBody
	@ApiOperation(httpMethod="POST",value="添加订单",notes="除了id,price,其他所有属性都要,deliver(送货)使用'是/否',json数据")
	public ServiceMarketResult addOrder(@RequestBody OrderDTO orderDTO){
		
		boolean flag=orderService.addOrder(orderDTO);
		
		return ServiceMarketResult.ok(flag);
	}
	
	//取消订购(用户)
	@RequestMapping(value="/DELETE/orders/{userId}/{orderId}")
	@ResponseBody
	@ApiOperation(httpMethod="DELETE",value="用户取消订单",notes="需要订单id和用户id")
	public ServiceMarketResult deleteOrders(@PathVariable(value="orderId") Integer orderId,@PathVariable(value="userId") Integer userId){
		
		//userId用于权限验证
		
		boolean flag=orderService.changeState(orderId,4);
		return ServiceMarketResult.ok(flag);
	}
	
	//接收订单
	@RequestMapping(value="/PATCH/acceptOrders/{userId}/{orderId}")
	@ResponseBody
	@ApiOperation(httpMethod="PATCH",value="商家接受订单",notes="需要订单id和商家的userId")
	public ServiceMarketResult acceptOrders(@PathVariable(value="orderId") Integer orderId,@PathVariable(value="userId") Integer userId){
		//身份权限验证(暂时未实现)：
		
		//如果身份权限正确，才可以接收订单：
		boolean flag = orderService.changeState(orderId,2);
		return ServiceMarketResult.ok(flag);
	}

	//拒绝订单
	@RequestMapping(value="/PATCH/refuseOrders/{userId}/{orderId}")
	@ResponseBody
	@ApiOperation(httpMethod="PATCH",value="商家拒绝订单",notes="需要订单id和商家的userId")
	public ServiceMarketResult refuseOrders(@PathVariable(value="orderId") Integer orderId,@PathVariable(value="userId") Integer userId){
		//身份权限验证(暂时未实现)：
		
		//如果身份权限正确，才可以接收订单：
		boolean flag = orderService.changeState(orderId,3);
		return ServiceMarketResult.ok(flag);
	}
	
	
	//用户修改订购信息
	@RequestMapping(value="/PUT/updateOrders")
	@ResponseBody
	@ApiOperation(httpMethod="PUT",value="用户修改订单信息",
	notes="商家接单后不能修改,即state==1是才可以修改，需要id、deliver、address、phone、remark")
	public ServiceMarketResult updateOrders(@RequestBody OrderDTO orderDTO){
		
		boolean flag=orderService.updateOrders(orderDTO);
		return ServiceMarketResult.ok(flag);
	}
	
	//查看订购
	@RequestMapping(value="/GET/orders/{shopId}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="查看商家订单",notes="需要商家id")
	public ServiceMarketResult getOrdersByShopid(@PathVariable(value="shopId")Integer shopId){
		List<OrdersListVo> list = orderService.ordersList(shopId);
		
		return ServiceMarketResult.ok(list);
	}
	
	//按订单接受状态查看订单
	@RequestMapping(value="/GET/ordersState/{shopId}/{state}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="根据订单接受状态查看订单",notes="需要shopId,state,1待接，2接受,3拒接")
	public ServiceMarketResult getOrderListByState(@PathVariable(value="state") String state,@PathVariable(value="shopId") Integer shopId){
		List<OrdersListVo> list = orderService.getOrderListByState(shopId,state);
		
		return ServiceMarketResult.ok(list);
	}
	
	
	//查询指定时间段的订单
	@RequestMapping(value="/GET/getOrderListByDate")
	@ResponseBody
	@ApiOperation(httpMethod="POST",value="查询指定时间段的订单",notes="需要shopId,startTime和endTime,时间使用dateTime形式,json格式")
	public ServiceMarketResult getOrderListByDate(@RequestBody DateDTO dateDTO){
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime=sdf.format(dateDTO.getStartTime());
		String endTime=sdf.format(dateDTO.getEndTime());
		Integer shopId=dateDTO.getShopId();
		
		List<OrdersListVo> list = orderService.getOrderListByDate(shopId,startTime,endTime);
		
		return ServiceMarketResult.ok(list);
	}

	
	//统计本月的订单量
	@RequestMapping(value="/GET/getOrdersNum")
	@ResponseBody
	@ApiOperation(httpMethod="POST",value="查询本月的订单量",notes="需要shopId,当前时间month,时间使用dateTime形式,json格式")
	public ServiceMarketResult getOrdersNum(@RequestBody DateDTO dateDTO){
		
		int num=orderService.getOrdersNum(dateDTO);
		return ServiceMarketResult.ok(num);
	}
	
	//查看我的订单
	@RequestMapping(value="/GET/getMyOrders/{userId}")
	@ResponseBody
	@ApiOperation(httpMethod="GET",value="查看我的订单",notes="需要userId")
	public ServiceMarketResult getMyOrders(@PathVariable(value="userId") Integer userId){
		List<OrdersListVo> list=orderService.getMyOrders(userId);
		
		return ServiceMarketResult.ok(list);
	}
	
}
