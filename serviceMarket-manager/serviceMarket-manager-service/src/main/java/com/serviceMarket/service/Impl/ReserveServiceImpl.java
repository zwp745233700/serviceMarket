package com.serviceMarket.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceMarket.DTO.DateDTO;
import com.serviceMarket.DTO.ReserveCartDTO;
import com.serviceMarket.DTO.ReserveDTO;
import com.serviceMarket.Vo.ReserveCartVo;
import com.serviceMarket.Vo.ReserveListVO;
import com.serviceMarket.mapper.GoodsMapper;
import com.serviceMarket.mapper.ReserveDetailsMapper;
import com.serviceMarket.mapper.ReserveMapper;
import com.serviceMarket.mapper.UserMapper;
import com.serviceMarket.pojo.Goods;
import com.serviceMarket.pojo.Reserve;
import com.serviceMarket.pojo.ReserveDetails;
import com.serviceMarket.pojo.ReserveDetailsExample;
import com.serviceMarket.pojo.ReserveExample;
import com.serviceMarket.pojo.ReserveExample.Criteria;
import com.serviceMarket.pojo.User;
import com.serviceMarket.service.ReserveService;

/**
 * *
 * 
 * @author Bling
 * @E-mail: zlh8013gsf@126.com
 * @date 创建时间：2017年11月24日 下午9:27:07 *
 * @version 1.0 *
 * @reason ReserveService的实现类
 */
@Service
public class ReserveServiceImpl implements ReserveService {

	@Autowired
	private ReserveMapper reserveMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private ReserveDetailsMapper reserveDetailsMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean saveReserve(ReserveDTO reserveDTO) {
		// TODO 不检验参数是否错误，直接使用
		Reserve reserve = new Reserve();
		reserve.setDes(reserveDTO.getDes());
		reserve.setPhone(reserveDTO.getPhone());
		reserve.setUserId(reserveDTO.getUserId());
		reserve.setShopId(reserveDTO.getShopId());
		reserve.setTakeTime(reserveDTO.getTakeTime());
		reserve.setIspay("N");
		reserve.setPay(0);

		// 设置此次预约的总价，状态，预约时间
		List<ReserveDetails> reserveDetailList = getGoodsList(reserveDTO.getShopCart());
		int sum = 0;
		for (ReserveDetails rd : reserveDetailList) {
			sum = sum + rd.getGoodsPrice()*rd.getGoodsNum();
		}
		reserve.setPrice(sum);
		reserve.setState(1);//1表示待接收
		reserve.setReserveTime(new Date());
		
		// 将预约信息存入预约表中，并且将预约id查询出
		int result = reserveMapper.insert(reserve);
		int reserveId = 0;
		if (result == 1) {
			reserveId = reserveMapper.returnId();
		} else {
			return false;
		}

		// 添加预约详情表的信息
		boolean isSuccess = addReserveDetail(reserveId, reserveDetailList);

		if (!isSuccess) {
			return false;
		}
		// 返回结果
		return true;
	}

	// 将预约详情表填充完成
	private boolean addReserveDetail(int reserveId, List<ReserveDetails> reserveDetailList) {
		for (ReserveDetails rd : reserveDetailList) {
			rd.setReserveId(reserveId);
			int result = reserveDetailsMapper.insert(rd);
			if (result != 1) {
				return false;
			}
		}
		return true;
	}

	// 获取预约列表的商品详情
	private List<ReserveDetails> getGoodsList(List<ReserveCartDTO> shopCart) {
		if (shopCart.size() == 0) {
			return null;
		} else {
			List<ReserveDetails> reserveDetailList = new ArrayList<>();
			for (ReserveCartDTO rcd : shopCart) {
				ReserveDetails rd = new ReserveDetails();
				Goods good = goodsMapper.selectByPrimaryKey(rcd.getId());
				rd.setGoodsId(good.getId());
				rd.setGoodsName(good.getName());
				rd.setGoodsNum(rcd.getNumber());
				rd.setGoodsPrice(good.getPrice());
				
				reserveDetailList.add(rd);
			}
			return reserveDetailList;
		}

	}

	
	@Override
	public List<ReserveListVO> reserveList(int shopId) {
		//从数据库中根据shopid来查找预约订单
		Reserve reserve = new Reserve();
		ReserveExample resEx = new ReserveExample();
		Criteria criteria = resEx.createCriteria();
		criteria.andShopIdEqualTo(shopId);
		List<Reserve> reList = reserveMapper.selectByExample(resEx);
		if(reList.size()==0){
			return null;
		}else{
			return conversion(reList);
			
		}
		
	}
	
	//将数据库中查询的预约订单赋值给ReserveListVO对象
	private List<ReserveListVO> conversion(List<Reserve> reList){
		List<ReserveListVO> list = new ArrayList<>();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String takeTime="";
		String reserveTime="";
		
		for(Reserve re : reList){
			ReserveListVO reVo = new ReserveListVO();

			reVo.setIsPay(re.getIspay());
			reVo.setPay(re.getPay());
			reVo.setDesc(re.getDes());
			reVo.setPhone(re.getPhone());
			reVo.setPrice(re.getPrice());
			reVo.setReserveId(re.getId());
			reVo.setUserId(re.getUserId());
			
			reserveTime=sdf.format(re.getReserveTime());
			takeTime=sdf.format(re.getTakeTime());
			reVo.setTakeTime(takeTime);
			reVo.setReserveTime(reserveTime);

			User user = userMapper.selectByPrimaryKey(re.getUserId());
			reVo.setUserName(user.getRealname());
			//根据预约订单的id查找预约详情的信息
			List<ReserveCartVo> reserveCart = getReserveCart(re.getId());
			reVo.setReserveCart(reserveCart);
			list.add(reVo);
		}
		return list;
	
	}
	
	
	//根据预约订单的id查找预约详情的信息
	private List<ReserveCartVo> getReserveCart(Integer id) {
		ReserveDetailsExample rde = new ReserveDetailsExample();
		com.serviceMarket.pojo.ReserveDetailsExample.Criteria criteria = rde.createCriteria();
		criteria.andReserveIdEqualTo(id);
		List<ReserveDetails> list = reserveDetailsMapper.selectByExample(rde);
		if(list.size()==0 || list.isEmpty()){
			return null;
		}else{
			List<ReserveCartVo> rcl = new ArrayList<>();
			for(ReserveDetails rd : list){
				ReserveCartVo rv = new ReserveCartVo();
				rv.setGoodsId(rd.getGoodsId());
				rv.setNumber(rd.getGoodsNum());
				rv.setPrice(rd.getGoodsPrice());
				rv.setName(rd.getGoodsName());
				rcl.add(rv);
			}
			return rcl;
		}
		
	}

	@Override
	public boolean acceptReserve(ReserveDTO reserveDTO) {
		//将预约订单查询出
		Reserve reserve = reserveMapper.selectByPrimaryKey(reserveDTO.getId());
		//修改预约单的状态
		reserve.setState(2);
		reserve.setPay(reserveDTO.getPay());
		reserve.setIspay("N");
		//将修改后的预约单更新到数据库中
		int result = reserveMapper.updateByPrimaryKeySelective(reserve);
		//如果更新成功，返回true，否则返回false
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	
	@Override
	public boolean changeReserveState(Integer reserveId,int state) {
		
		Reserve reserve=new Reserve();
		reserve.setId(reserveId);
		if(state==5){
			reserve.setState(state);
			reserve.setIspay("Y");
		}else if(state==3){
			reserve.setState(state);
		}else if(state==4){
			reserve.setState(state);
		}else{
			return false;
		}
		
		int result =reserveMapper.updateByPrimaryKeySelective(reserve);
		if(result==1){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateReserve(ReserveDTO reserveDTO) {
		Reserve reserve=new Reserve();
		reserve.setId(reserveDTO.getId());
		reserve.setDes(reserveDTO.getDes());
		reserve.setPhone(reserveDTO.getPhone());
		reserve.setTakeTime(reserveDTO.getTakeTime());
		
		int result = reserveMapper.updateByPrimaryKeySelective(reserve);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<ReserveListVO> getReserveListByState(Integer shopId, Integer state) {
		ReserveExample example = new ReserveExample();
		Criteria criteria=example.createCriteria();
		//state:1用户预约,2商家确认并填写定金,3商家拒接,5用户再次确认并支付定金(即预约成功)
		criteria.andStateEqualTo(state);
		criteria.andShopIdEqualTo(shopId);
		
		List<Reserve> reList = reserveMapper.selectByExample(example);
		if(reList.size()==0){
			return null;
		}else{
			return conversion(reList);
		}
	}

	@Override
	public List<ReserveListVO> getReserveListByDate(Integer shopId, String startTime, String endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("startTime",startTime);
		map.put("endTime",endTime);
		map.put("shopId", shopId);
		
		List<Reserve> reserveList=reserveMapper.getReserveListByDate(map);
		if(reserveList.size()==0){
			return null;
		}else{
			return conversion(reserveList);
		}
	}

	@Override
	public int getReserveNum(DateDTO dateDTO) {
		SimpleDateFormat sdf1 = new SimpleDateFormat( "yyyy-MM");
		String month=sdf1.format(dateDTO.getMonth());
		
		String start=month+"-01 00:00:00";
		String end=month+"-31 23:59:59";
		SimpleDateFormat sdf2 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		int num=0;
		
		try {
			Date startTime =  sdf2.parse(start);
			Date endTime =  sdf2.parse(end);
			
			ReserveExample example = new ReserveExample();
			Criteria criteria = example.createCriteria();
			criteria.andShopIdEqualTo(dateDTO.getShopId());
			criteria.andReserveTimeBetween(startTime, endTime);
			
			num=reserveMapper.countByExample(example);
		}catch(Exception e){
			return 0;
		}
		return num;
}

	@Override
	public List<ReserveListVO> getMyReserve(Integer userId) {
		
		ReserveExample example = new ReserveExample();
		Criteria criteria=example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		
		List<Reserve> list=reserveMapper.selectByExample(example);
		
		if(list.size()==0){
			return null;
		}else{
			return conversion(list);
		}
	}
}
