package com.serviceMarket.service;

import java.util.List;

import com.serviceMarket.DTO.DateDTO;
import com.serviceMarket.DTO.ReserveDTO;
import com.serviceMarket.Vo.ReserveListVO;

/**
 *    预约service类
	  @author  Bling 
	  @E-mail: zlh8013gsf@126.com
  	  @date 创建时间：2017年11月24日 下午8:48:46 * 
	  @version 1.0 *
 */

public interface ReserveService {

	/**
	 * 保存用户预约的订单
	 * @param reserveDTO
	 * @return
	 */
	public boolean saveReserve(ReserveDTO reserveDTO);
	
	//TODO 分页
	/**
	 * 查询某商家的的预约列表
	 * @param shopId
	 * @return
	 */
	public List<ReserveListVO> reserveList(int shopId);
	
	/**
	 * 商家接受订单
	 * @param shopId
	 * @param reserveId
	 * @return
	 */
	public boolean acceptReserve(ReserveDTO reserveDTO);
	
	//接受、拒绝、取消预约
	public boolean changeReserveState(Integer reserveId,int state);
	
	//修改订单信息
	public boolean updateReserve(ReserveDTO reserveDTO);
	
	//按预约状态查看预约单
	public List<ReserveListVO> getReserveListByState(Integer shopId,Integer state);
	
	//查看指定时间段的预约单
	public List<ReserveListVO> getReserveListByDate(Integer shopId,String startTime,String endTime);
	
	//统计本月预约量
	public int getReserveNum(DateDTO dateDTO);
	
	//查看我的预约
	public List<ReserveListVO> getMyReserve(Integer userId);
}
