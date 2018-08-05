package com.serviceMarket.mapper;

import com.serviceMarket.pojo.Reserve;
import com.serviceMarket.pojo.ReserveExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ReserveMapper {
    int countByExample(ReserveExample example);

    int deleteByExample(ReserveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Reserve record);
    
    int returnId();

    int insertSelective(Reserve record);

    List<Reserve> selectByExample(ReserveExample example);

    Reserve selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Reserve record, @Param("example") ReserveExample example);

    int updateByExample(@Param("record") Reserve record, @Param("example") ReserveExample example);

    int updateByPrimaryKeySelective(Reserve record);

    int updateByPrimaryKey(Reserve record);
    
    List<Reserve> getReserveListByDate(Map map);
}