package com.serviceMarket.mapper;

import com.serviceMarket.pojo.Groups;
import com.serviceMarket.pojo.GroupsExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface GroupsMapper {
    int countByExample(GroupsExample example);

    int deleteByExample(GroupsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Groups record);

    int insertSelective(Groups record);

    List<Groups> selectByExample(GroupsExample example);

    Groups selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Groups record, @Param("example") GroupsExample example);

    int updateByExample(@Param("record") Groups record, @Param("example") GroupsExample example);

    int updateByPrimaryKeySelective(Groups record);

    int updateByPrimaryKey(Groups record);
    
    List<Groups> getStartingGroup(Map map);
    
    List<Groups> getWillStartingGroup(Map map);
    
    List<Groups> getMarketStartingGroup(Map map);
    
    List<Groups> getMarketWillStartGroup(Map map);
    
}