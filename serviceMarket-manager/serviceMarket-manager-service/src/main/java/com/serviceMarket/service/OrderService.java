package com.serviceMarket.service;

import java.util.List;

import com.serviceMarket.DTO.DateDTO;
import com.serviceMarket.DTO.OrderDTO;
import com.serviceMarket.Vo.OrdersListVo;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2017年11月27日 下午12:49:36 
* 类说明 :OrderService接口
*/
public interface OrderService {

	//添加用户订单
	public boolean addOrder(OrderDTO orderDTO);

	//查看订单列表
	public List<OrdersListVo> ordersList(Integer shopId);

	//修改订单状态
	public boolean changeState(Integer orderId,int state);
	
	//修改订购信息(用户：商家接单后不能修改)
	public boolean updateOrders(OrderDTO orderDTO);
	
	//按订单接受状态查看订单
	public List<OrdersListVo> getOrderListByState(Integer shopId,String state);
	
	//查看指定时间段的订单
	public List<OrdersListVo> getOrderListByDate(Integer shopId, String startTime,String endTime);

	//统计本月的订单量
	public int getOrdersNum(DateDTO dateDTO);
	
	//查看我的订单
	public List<OrdersListVo> getMyOrders(Integer userId);
}
