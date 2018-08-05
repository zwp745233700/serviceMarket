package com.serviceMarket.mapper;

import com.serviceMarket.pojo.State;
import com.serviceMarket.pojo.StateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StateMapper {
    int countByExample(StateExample example);

    int deleteByExample(StateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(State record);

    int insertSelective(State record);

    List<State> selectByExample(StateExample example);

    State selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") State record, @Param("example") StateExample example);

    int updateByExample(@Param("record") State record, @Param("example") StateExample example);

    int updateByPrimaryKeySelective(State record);

    int updateByPrimaryKey(State record);
}