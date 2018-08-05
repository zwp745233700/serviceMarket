package com.serviceMarket.service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceMarket.DTO.DateDTO;
import com.serviceMarket.DTO.OrderCartDTO;
import com.serviceMarket.DTO.OrderDTO;
import com.serviceMarket.Vo.OrdersCartVo;
import com.serviceMarket.Vo.OrdersListVo;
import com.serviceMarket.mapper.GoodsMapper;
import com.serviceMarket.mapper.OrderDetailsMapper;
import com.serviceMarket.mapper.OrdersMapper;
import com.serviceMarket.mapper.UserMapper;
import com.serviceMarket.pojo.Goods;
import com.serviceMarket.pojo.Orders;
import com.serviceMarket.pojo.OrdersExample;
import com.serviceMarket.pojo.Reserve;
import com.serviceMarket.pojo.OrdersExample.Criteria;
import com.serviceMarket.pojo.User;
import com.serviceMarket.pojo.OrderDetails;
import com.serviceMarket.pojo.OrderDetailsExample;
import com.serviceMarket.service.OrderService;

/** 
* @author 张维鹏
* @version 2017年11月27日 12:50:41
* 类说明：订单实现类
*/


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private OrderDetailsMapper orderDetailsMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private UserMapper userMapper;
	
	@Override
	//增加订单
	public boolean addOrder(OrderDTO orderDTO) {
		Orders orders =new Orders();
		
		orders.setAddress(orderDTO.getAddress());//收货地址
		orders.setIsDeliver(orderDTO.getDeliver());//是否送货
		orders.setOrderTime(new Date());//下单时间
		orders.setPhone(orderDTO.getPhone());//联系电话
		orders.setRemark(orderDTO.getRemark());//备注
		orders.setShopId(orderDTO.getShopId());//商店id
		orders.setUserId(orderDTO.getUserId());//用户id
		orders.setState(1+"");//1表示订单已经提交，等待商家确认
		
		//获取购物车的商品的基本信息，并计算总价格
		List<OrderDetails> orderDetailsList=getShopCart(orderDTO.getShopCart());
		int sum=0;
		for(OrderDetails orderDetails:orderDetailsList){
			sum=sum+orderDetails.getPrice()*orderDetails.getGoodsNum();
		}
		orders.setPrice(sum);
		
		//添加订单，并返回生成的id
		ordersMapper.insertSelective(orders);
		//添加失败
		if(orders.getId()==0){
			return false;
		}else{
			//添加成功，则继续添加订单详情表
			boolean flag=addOrderDetail(orders.getId(),orderDetailsList);
			
			return flag;
		}
	}

	
	//添加订单详情：
	private boolean addOrderDetail(int orderId, List<OrderDetails> orderDetailsList) {
		for(OrderDetails orderDetails:orderDetailsList){
			orderDetails.setOrderId(orderId);
			int result=orderDetailsMapper.insert(orderDetails);
			if(result!=1){
				return false;
			}
		}
		return true;
	}

	//查询用户购物车内的商品的基本信息详情：
	private List<OrderDetails> getShopCart(List<OrderCartDTO> shopCart) {
		
		if(shopCart.size()==0){
			return null;
		}else{
			List<OrderDetails> orderDetailsList=new ArrayList<>();
			for(OrderCartDTO orderCartDTO:shopCart){
				
				OrderDetails orderDetails=new OrderDetails();
				Goods good=goodsMapper.selectByPrimaryKey(orderCartDTO.getId());//根据id查询商品信息
				orderDetails.setGoodsId(good.getId());
				orderDetails.setGoodsName(good.getName());
				orderDetails.setGoodsNum(orderCartDTO.getNum());
				orderDetails.setPrice(good.getPrice());
				
				//在goods表中减少相应的数量
				good.setNumber(good.getNumber()-orderCartDTO.getNum());
				goodsMapper.updateByPrimaryKeySelective(good);
				
				orderDetailsList.add(orderDetails);
			}
			return orderDetailsList;
		}
	}


	@Override
	//查看订单列表：根据商店id
	public List<OrdersListVo> ordersList(Integer shopId) {
		
		OrdersExample ordersExample=new OrdersExample();
		Criteria criteria = ordersExample.createCriteria();
		criteria.andShopIdEqualTo(shopId);
		List<Orders> ordersList=ordersMapper.selectByExample(ordersExample);//根据商店Id查询所有的订单；
		
		if(ordersList.size()==0){
			return null;
		}else{
			List<OrdersListVo> list=conversion(ordersList);
			return list;
		}
	}

	
	//查询订单中的购物车的商品信息：
	private List<OrdersCartVo> getOrdersCart(Integer orderId) {
		//设置查询条件：
		OrderDetailsExample orderDetailsExample=new OrderDetailsExample();
		com.serviceMarket.pojo.OrderDetailsExample.Criteria criteria=orderDetailsExample.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		//根据订单id查询，返回一个订单详情列表：
		List<OrderDetails> orderDetailsList=orderDetailsMapper.selectByExample(orderDetailsExample);
		
		//遍历，将订单详情列表的信息赋值给到List<OrdersCartVo>中，并返回。
		List<OrdersCartVo> list=new ArrayList<>();
		for(OrderDetails orderDetails:orderDetailsList){
			OrdersCartVo ordersCartVo=new OrdersCartVo();
			
			ordersCartVo.setGoodsId(orderDetails.getGoodsId());
			ordersCartVo.setName(orderDetails.getGoodsName());
			ordersCartVo.setNumber(orderDetails.getGoodsNum());
			ordersCartVo.setPrice(orderDetails.getPrice());
			
			list.add(ordersCartVo);
		}
		return list;
	}

	//修改订单状态
	public boolean changeState(Integer orderId,int state){
		Orders orders=new Orders();
		orders.setId(orderId);
		
		if(state==2){
			orders.setState(2+"");//商家接单
		}else if(state==3){
			orders.setState(3+"");//商家拒单
		}else if(state==4){
			orders.setState(4+"");//用户取消
		}else{
			return false;
		}
		
		int result=ordersMapper.updateByPrimaryKeySelective(orders);
		if(result==1){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateOrders(OrderDTO orderDTO) {
		
		Orders orders=new Orders();
		orders.setId(orderDTO.getId());
		orders.setAddress(orderDTO.getAddress());
		orders.setIsDeliver(orderDTO.getDeliver());
		orders.setPhone(orderDTO.getPhone());
		orders.setRemark(orderDTO.getRemark());
		int result = ordersMapper.updateByPrimaryKeySelective(orders);
		
		if(result==1){
			return true;
		}
		return false;
	}


	@Override
	public List<OrdersListVo> getOrderListByState(Integer shopId,String state) {
		
		OrdersExample ordersExample=new OrdersExample();
		Criteria criteria = ordersExample.createCriteria();
		criteria.andStateEqualTo(state);
		criteria.andShopIdEqualTo(shopId);
		
		List<Orders> ordersList=ordersMapper.selectByExample(ordersExample);//根据商店Id查询所有的订单；
		
		if(ordersList.size()==0){
			return null;
		}else{
			List<OrdersListVo> list=conversion(ordersList);
			return list;
		}
	}


	@Override
	public List<OrdersListVo> getOrderListByDate(Integer shopId,String startTime,String endTime){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("startTime",startTime);
		map.put("endTime",endTime);
		map.put("shopId", shopId);

		List<Orders> ordersList=ordersMapper.getOrderListByDate(map);
		
		if(ordersList.size()==0){
			return null;
		}else{
			List<OrdersListVo> list=conversion(ordersList);
			return list;
		}
	}
	
	
	//将查询出来的ordersList赋值给OrdersListVo对象：
	private List<OrdersListVo> conversion(List<Orders> ordersList){
		List<OrdersListVo> list=new ArrayList<>();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime="";
		
		for(Orders orders:ordersList){
			OrdersListVo ordersVo=new OrdersListVo();
			ordersVo.setAddress(orders.getAddress());
			ordersVo.setDeliver(orders.getIsDeliver());
			ordersVo.setOrderId(orders.getId());
			ordersVo.setPhone(orders.getPhone());
			ordersVo.setPrice(orders.getPrice());
			ordersVo.setRemark(orders.getRemark());
			ordersVo.setShopId(orders.getShopId());
			ordersVo.setUserId(orders.getUserId());
			
			createTime=sdf.format(orders.getOrderTime());
			ordersVo.setCreateTime(createTime);
			
			User user=userMapper.selectByPrimaryKey(orders.getUserId());
			ordersVo.setUsername(user.getRealname());//设置用户名
			
			//查询订单中的购物车的商品信息列表：
			List<OrdersCartVo> goodList=getOrdersCart(orders.getId());
			ordersVo.setGoodsList(goodList);
			
			list.add(ordersVo);
		}
		return list;
	}


	@Override
	public int getOrdersNum(DateDTO dateDTO) {
		
		SimpleDateFormat sdf1 = new SimpleDateFormat( "yyyy-MM");
		String month=sdf1.format(dateDTO.getMonth());
		
		String start=month+"-01 00:00:00";
		String end=month+"-31 23:59:59";
		
		SimpleDateFormat sdf2 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		int num=0;
		
		try {
			Date startTime =  sdf2.parse(start);
			Date endTime =  sdf2.parse(end);
			
			OrdersExample example =new OrdersExample();
			Criteria criteria=example.createCriteria();
			criteria.andShopIdEqualTo(dateDTO.getShopId());
			criteria.andOrderTimeBetween(startTime, endTime);
			
			num=ordersMapper.countByExample(example);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return num;
	}


	@Override
	public List<OrdersListVo> getMyOrders(Integer userId) {
		
		OrdersExample example=new OrdersExample();
		Criteria criteria=example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<Orders> ordersList=ordersMapper.selectByExample(example);
		
		if(ordersList.size()==0){
			return null;
		}else{
			List<OrdersListVo> list=conversion(ordersList);
			return list;
		}
	}
}
