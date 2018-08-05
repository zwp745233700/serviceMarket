package com.serviceMarket.mapper;

import com.serviceMarket.pojo.HelperAccept;
import com.serviceMarket.pojo.HelperAcceptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HelperAcceptMapper {
    int countByExample(HelperAcceptExample example);

    int deleteByExample(HelperAcceptExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HelperAccept record);

    int insertSelective(HelperAccept record);

    List<HelperAccept> selectByExample(HelperAcceptExample example);

    HelperAccept selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HelperAccept record, @Param("example") HelperAcceptExample example);

    int updateByExample(@Param("record") HelperAccept record, @Param("example") HelperAcceptExample example);

    int updateByPrimaryKeySelective(HelperAccept record);

    int updateByPrimaryKey(HelperAccept record);
}