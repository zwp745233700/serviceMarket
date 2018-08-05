package com.serviceMarket.service.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.serviceMarket.DTO.HelpAccepetDTO;
import com.serviceMarket.DTO.HelperTableDTO;
import com.serviceMarket.DTO.StateDTO;
import com.serviceMarket.Vo.AccepterVo;
import com.serviceMarket.Vo.HelperAcceptVo;
import com.serviceMarket.Vo.HelperTableVo;
import com.serviceMarket.Vo.StateVo;
import com.serviceMarket.common.pojo.PageDateResult;
import com.serviceMarket.mapper.HelperAcceptMapper;
import com.serviceMarket.mapper.HelperTableMapper;
import com.serviceMarket.mapper.PermissionsMapper;
import com.serviceMarket.mapper.StateMapper;
import com.serviceMarket.mapper.UserMapper;
import com.serviceMarket.pojo.HelperAccept;
import com.serviceMarket.pojo.HelperAcceptExample;
import com.serviceMarket.pojo.HelperTable;
import com.serviceMarket.pojo.HelperTableExample;
import com.serviceMarket.pojo.HelperTableExample.Criteria;
import com.serviceMarket.pojo.Permissions;
import com.serviceMarket.pojo.PermissionsExample;
import com.serviceMarket.pojo.State;
import com.serviceMarket.pojo.StateExample;
import com.serviceMarket.pojo.User;
import com.serviceMarket.service.HelperService;

/** 
* @author 作者 张维鹏: 
* @version 创建时间：2018年2月2日 下午1:51:14 
* 类说明 
*/
@Service
public class HelperServiceImpl implements HelperService {
	
	@Autowired
	private PermissionsMapper permMapper;
	@Autowired
	private StateMapper stateMapper;
	@Autowired
	private HelperAcceptMapper helpAcceptMapper;
	@Autowired
	private HelperTableMapper helpTableMapper;
	@Autowired
	private UserMapper userMapper;
	

	//注册成为代购者
	@Override
	public boolean helperRegister(Integer userId) {
		
		PermissionsExample example = new PermissionsExample();
		com.serviceMarket.pojo.PermissionsExample.Criteria criteria=example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		
		List<Permissions> list=permMapper.selectByExample(example);
		if(!list.isEmpty()){
			Permissions perm=list.get(0);
			perm.setIsHelper("Y");
			int result=permMapper.updateByPrimaryKeySelective(perm);
			if(result==1){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}


	//发起代购
	@Override
	public boolean addHelpRequest(HelperTableDTO helperTableDTO) {
		HelperTable helperTable=new HelperTable();
		helperTable.setAddress(helperTableDTO.getAddress());
		helperTable.setArriveTime(helperTableDTO.getArriveTime());
		helperTable.setListing(helperTableDTO.getListing());
		helperTable.setPhone(helperTableDTO.getPhone());
		helperTable.setPrice(helperTableDTO.getPrice());
		helperTable.setUserId(helperTableDTO.getUserId());
		helperTable.setState(1);//1表示待接收，2表示已被接收
		helperTable.setTime(new Date());
		
		int result=helpTableMapper.insertSelective(helperTable);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}


	//查看我的代购请求
	@Override
	public List<HelperTableVo> getMyHelperTable(Integer userId) 
	{
		List<HelperTableVo> HelpTableList=new ArrayList<HelperTableVo>();
	
		HelperTableExample htExample=new HelperTableExample();
		Criteria criteria=htExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<HelperTable> helperTableList=helpTableMapper.selectByExample(htExample);
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time="";
		String arriveTime="";
		
		for(HelperTable helperTable:helperTableList){
			HelperTableVo helperTableVo=new HelperTableVo();
			
			time=sdf.format(helperTable.getTime());
			arriveTime=sdf.format(helperTable.getArriveTime());
			
			helperTableVo.setArriveTime(arriveTime);
			helperTableVo.setTime(time);
			helperTableVo.setAddress(helperTable.getAddress());
			helperTableVo.setId(helperTable.getId());
			helperTableVo.setListing(helperTable.getListing());
			helperTableVo.setPhone(helperTable.getPhone());
			helperTableVo.setPrice(helperTable.getPrice());
			helperTableVo.setUserId(helperTable.getUserId());
			helperTableVo.setState(helperTable.getState());
			
			
			//设置helperAccept和state表的内容：
			if(helperTable.getState()==1){//1表示待处理
				helperTableVo.setStateVo(null);
				helperTableVo.setAccepterVoList(null);
				
			}else if(helperTable.getState()==2){//2表示已经有代购者接收请求了
				helperTableVo.setStateVo(null);
				
				HelperAcceptExample hacExample=new HelperAcceptExample();
				com.serviceMarket.pojo.HelperAcceptExample.Criteria hacCriteria=hacExample.createCriteria();
				hacCriteria.andHelperTableIdEqualTo(helperTable.getId());
				hacCriteria.andStateEqualTo(1);
				List<AccepterVo> accepterVoList=getMyAccepterVoList(hacExample);
				
				helperTableVo.setAccepterVoList(accepterVoList);
				
			}else if(helperTable.getState()==3){//3表示用户已经再次同意代购者
				
				HelperAcceptExample hacExample=new HelperAcceptExample();
				com.serviceMarket.pojo.HelperAcceptExample.Criteria hacCriteria=hacExample.createCriteria();
				hacCriteria.andHelperTableIdEqualTo(helperTable.getId());
				hacCriteria.andStateEqualTo(2);
				List<AccepterVo> accepterVoList=getMyAccepterVoList(hacExample);
				
				helperTableVo.setAccepterVoList(accepterVoList);
				
				//设置stateVo
				StateVo stateVo=getStateVoByHTid(helperTable.getId());
				helperTableVo.setStateVo(stateVo);
			}
			
			HelpTableList.add(helperTableVo);
		}
		
		return HelpTableList;
	}
	
	
	@Override
	public boolean updateHelperTable(HelperTableDTO helperTableDTO) {
		//查看订单状态是否state==1
		int state=helperTableDTO.getState();
		
		if(state!=1){
			return false;
		}else{//修改代购请求
			HelperTable helperTable=new HelperTable();
			
			helperTable.setId(helperTableDTO.getId());
			helperTable.setAddress(helperTableDTO.getAddress());
			helperTable.setArriveTime(helperTableDTO.getArriveTime());
			helperTable.setListing(helperTableDTO.getListing());
			helperTable.setPhone(helperTableDTO.getPhone());
			helperTable.setPrice(helperTableDTO.getPrice());
			
			int result=helpTableMapper.updateByPrimaryKeySelective(helperTable);
			
			if(result==1){
				return true;
			}else{
				return false;
			}
		}
	}


	@Override
	public boolean deleteHelperTable(Integer helpTableId) {
		HelperTable helperTable =helpTableMapper.selectByPrimaryKey(helpTableId);
		if(helperTable.getState()==1 || helperTable.getState()==2){
			int result=helpTableMapper.deleteByPrimaryKey(helpTableId);
			if(result==1){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	@Override
	public PageDateResult getAllHelperTable(int page,int rows) {
		List<HelperTableVo> HelpTableList=new ArrayList<HelperTableVo>();
		
		HelperTableExample example= new HelperTableExample();
		Criteria criteria=example.createCriteria();
		
		//添加查询条件
		List<Integer> values=new ArrayList<Integer>();//表示状态的数组
		values.add(1);//1表示没有代购者接单
		values.add(2);//2表示已经有代沟者接单
		criteria.andStateIn(values);
		
		criteria.andArriveTimeGreaterThan(new Date());
		
		//分页处理
		PageHelper.startPage(page, rows);//page是第几页，rows表示每页的行数
		
		List<HelperTable> helperTableList=helpTableMapper.selectByExample(example);
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time="";
		String arriveTime="";
		
		for(HelperTable helperTable:helperTableList){
			HelperTableVo helperTableVo=new HelperTableVo();
			
			time=sdf.format(helperTable.getTime());
			arriveTime=sdf.format(helperTable.getArriveTime());
			
			helperTableVo.setTime(time);
			helperTableVo.setArriveTime(arriveTime);
			helperTableVo.setAddress(helperTable.getAddress());
			helperTableVo.setId(helperTable.getId());
			helperTableVo.setListing(helperTable.getListing());
			helperTableVo.setPhone(helperTable.getPhone());
			helperTableVo.setPrice(helperTable.getPrice());
			helperTableVo.setState(helperTable.getState());
			helperTableVo.setUserId(helperTable.getUserId());
			
			User user=userMapper.selectByPrimaryKey(helperTable.getUserId());
			helperTableVo.setUsername(user.getUser());
				
			HelpTableList.add(helperTableVo);
		}
		
		//创建一个返回值对象
		PageDateResult result=new PageDateResult();
		result.setRows(HelpTableList);
		//取记录总条数
		PageInfo<HelperTable> pageInfo=new PageInfo<>(helperTableList);
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}

	//代购者接受代购
	@Override
	public boolean acceptRequest(HelpAccepetDTO helpAccepetDTO) {
		
		//设置helper_table表的状态
		HelperTable helperTable=helpTableMapper.selectByPrimaryKey(helpAccepetDTO.getHelperTableId());
		helperTable.setState(2);
		int result1=helpTableMapper.updateByPrimaryKeySelective(helperTable);
		if(result1!=1){
			return false;
		}
		
		HelperAccept helperAccept=new HelperAccept();
		helperAccept.setAcceptTime(new Date());
		helperAccept.setDeadTime(helpAccepetDTO.getDeadTime());
		helperAccept.setHelperId(helpAccepetDTO.getHelperId());
		helperAccept.setHelperTableId(helpAccepetDTO.getHelperTableId());
		helperAccept.setPhone(helpAccepetDTO.getPhone());
		helperAccept.setState(1);
		
		//插入代购者表
		int result=helpAcceptMapper.insert(helperAccept);
		
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	
	//代购者查看自己接受的代购
	@Override
	public List<HelperAcceptVo> getMyAccept(Integer helperId) {
		
		List<HelperAcceptVo> list=new ArrayList<HelperAcceptVo>();
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String acceptTime="";
		String deadTime="";
		
		HelperAcceptExample haExample=new HelperAcceptExample();
		com.serviceMarket.pojo.HelperAcceptExample.Criteria criteria=haExample.createCriteria();
		criteria.andHelperIdEqualTo(helperId);
		List<HelperAccept> helperAcceptList=helpAcceptMapper.selectByExample(haExample);
		
		for(HelperAccept helperAccept:helperAcceptList){
			HelperAcceptVo helperAcceptVo=new HelperAcceptVo();
			
			acceptTime=sdf.format(helperAccept.getAcceptTime());
			deadTime=sdf.format(helperAccept.getDeadTime());

			helperAcceptVo.setDeadTime(deadTime);
			helperAcceptVo.setAcceptTime(acceptTime);
			helperAcceptVo.setHelperId(helperAccept.getHelperId());
			helperAcceptVo.setHelperTableId(helperAccept.getHelperTableId());
			helperAcceptVo.setId(helperAccept.getId());
			helperAcceptVo.setPhone(helperAccept.getPhone());
			helperAcceptVo.setState(helperAccept.getState());
			
			HelperTableVo helperTableVo =getHelperTableVoById(helperAccept.getHelperTableId());
			helperAcceptVo.setHelperTable(helperTableVo);

			if(helperAccept.getState()==2){
				//state==2表示用户已经同意
				StateVo stateVo = getStateVoByHTid(helperTableVo.getId());
				helperAcceptVo.setStateVo(stateVo);
			}
			
			list.add(helperAcceptVo);
		}
		
		return list;
	}
	
	
	@Override
	public boolean deleteAccept(Integer helperAcceptId) {
		HelperAccept helperAccept =helpAcceptMapper.selectByPrimaryKey(helperAcceptId);
		
		int state=helperAccept.getState();
		if(state==2){
			//表示已经被用户确认同意，不能执行删除
			return false;
		}else{//表示未被用户同意，可以删除
			/*	
			//先修改helper_table表的状态
			HelperTable helperTable=helpTableMapper.selectByPrimaryKey(helperAccept.getHelperTableId());
			helperTable.setState(1);
			int result=helpTableMapper.updateByPrimaryKeySelective(helperTable);
			if(result==1){
				//删除helper_accept的数据
				int flag=helpAcceptMapper.deleteByPrimaryKey(helperAcceptId);
				if(flag==1){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}*/
			
			int result=helpAcceptMapper.deleteByPrimaryKey(helperAcceptId);
			if(result==1){
				return true;
			}else{
				return false;
			}
		}
	}
	
	
	@Override
	public boolean agreeHelper(Integer helpTableId, Integer acceptId) {
		//校检两个参数是否正确，暂时忽略
		
		HelperTable helperTable=new HelperTable();
		helperTable.setId(helpTableId);
		helperTable.setState(3);
		
		HelperAccept helperAccept=helpAcceptMapper.selectByPrimaryKey(acceptId);
		helperAccept.setState(2);
		
		int result1=helpTableMapper.updateByPrimaryKeySelective(helperTable);
		int result2=helpAcceptMapper.updateByPrimaryKeySelective(helperAccept);
		
		if(result1+result2==2){
			//创建state表
			State state=new State();
			state.setHelperTableId(helpTableId);
			state.setHelperId(helperAccept.getHelperId());
			state.setUserAcceptState(1);
			state.setArriveState(1);
			state.setCreateTime(new Date());
			
			int result=stateMapper.insert(state);
			if(result==1){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	
	@Override
	public boolean refuseHelper(Integer acceptId) {
		//校检参数是否正确，暂时忽略
		
		HelperAccept helperAccept=helpAcceptMapper.selectByPrimaryKey(acceptId);
		helperAccept.setState(3);
		
		int result=helpAcceptMapper.updateByPrimaryKeySelective(helperAccept);
		
		if(result==1){
			return true;
		}else{
			return false;
		}
	}


	@Override
	public boolean receiveGoods(int i, int stateId) {
		
		State state=stateMapper.selectByPrimaryKey(stateId);
		if(i==1){
			state.setUserAcceptState(2);
		}else if(i==2){
			state.setArriveState(2);
		}
		
		if(state.getArriveTime()==null || "".equals(state.getArriveTime())){
			state.setArriveTime(new Date());
		}
		
		int result = stateMapper.updateByPrimaryKeySelective(state);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}


	@Override
	public boolean lossGoods(int i, int stateId) {
		
		State state=stateMapper.selectByPrimaryKey(stateId);
		if(i==1){
			state.setUserAcceptState(3);
		}else if(i==2){
			state.setArriveState(3);
		}
		
		int result = stateMapper.updateByPrimaryKeySelective(state);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}

	//用户评论
	@Override
	public boolean evaluation(StateDTO stateDTO) {
		
		int i=stateDTO.getUserAcceptState();
		if(i==1){
			return false;
		}else{
			State state=new State();
			state.setId(stateDTO.getId());
			state.setAppraise(stateDTO.getAppraise());
			
			int result=stateMapper.updateByPrimaryKeySelective(state);
			if(result==1){
				return true;
			}else{
				return false;
			}
		}
		
	}
	

	
	//通过helper_table_id获得helperTableVo
	private HelperTableVo getHelperTableVoById(Integer helpTableId){

		HelperTableVo helperTableVo=new HelperTableVo();
		
		HelperTable helperTable=helpTableMapper.selectByPrimaryKey(helpTableId);
		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time="";
		String arriveTime="";
		time=sdf.format(helperTable.getTime());
		arriveTime=sdf.format(helperTable.getArriveTime());
		
		helperTableVo.setAddress(helperTable.getAddress());
		helperTableVo.setArriveTime(arriveTime);
		helperTableVo.setTime(time);
		helperTableVo.setId(helperTable.getId());
		helperTableVo.setListing(helperTable.getListing());
		helperTableVo.setPhone(helperTable.getPhone());
		helperTableVo.setPrice(helperTable.getPrice());
		helperTableVo.setState(helperTable.getState());
		
		helperTableVo.setUserId(helperTable.getUserId());
		User user=userMapper.selectByPrimaryKey(helperTable.getUserId());
		helperTableVo.setUsername(user.getUser());
		
		return helperTableVo;
	}
	
	
	//通过helper_table_id获得stateVo
	private StateVo getStateVoByHTid(Integer helpTableId){
		
		StateExample stateExample=new StateExample();
		com.serviceMarket.pojo.StateExample.Criteria stateCriteria=stateExample.createCriteria();
		stateCriteria.andHelperTableIdEqualTo(helpTableId);
		List<State> stateList=stateMapper.selectByExample(stateExample);
		
		if(!stateList.isEmpty()){
			
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String arriveTime="";
			String createTime="";
			
			State state=stateList.get(0);
			
			StateVo stateVo=new StateVo();
			if(state.getAppraise()!=null){
				stateVo.setAppraise(state.getAppraise());
			}
			if(state.getArriveTime()!=null && !("".equals(state.getArriveTime()))){
				arriveTime=sdf.format(state.getArriveTime());
				stateVo.setArriveTime(arriveTime);
			}
			
			createTime=sdf.format(state.getCreateTime());
			stateVo.setCreateTime(createTime);
			
			stateVo.setArriveState(state.getArriveState());
			stateVo.setUserAcceptState(state.getUserAcceptState());
			
			stateVo.setId(state.getId());
			stateVo.setHelperId(state.getHelperId());
			stateVo.setHelperTableId(state.getHelperTableId());
			
			return stateVo;
		}else{
			return null;
		}
	}	
	
	
	//设置查看我的代购：代购者集合
	private List<AccepterVo> getMyAccepterVoList(HelperAcceptExample hacExample){
		List<AccepterVo> accepterVoList=new ArrayList<AccepterVo>();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		List<HelperAccept> hacList=helpAcceptMapper.selectByExample(hacExample);
	
		if(!hacList.isEmpty()){
			String acceptTime="";
			String deadTime="";
			
			for(HelperAccept helperAccept:hacList){
				AccepterVo accepterVo=new AccepterVo();
				
				acceptTime=sdf.format(helperAccept.getAcceptTime());
				deadTime=sdf.format(helperAccept.getDeadTime());
				accepterVo.setAcceptTime(acceptTime);
				accepterVo.setDeadTime(deadTime);
				
				accepterVo.setHelperTableId(helperAccept.getHelperTableId());
				accepterVo.setId(helperAccept.getId());
				accepterVo.setPhone(helperAccept.getPhone());
				accepterVo.setState(helperAccept.getState());
				accepterVo.setHelperId(helperAccept.getHelperId());
				User helper=userMapper.selectByPrimaryKey(helperAccept.getHelperId());
				accepterVo.setHelperName(helper.getUser());
				
				accepterVoList.add(accepterVo);
			}
		}
		return accepterVoList;
	}
}
