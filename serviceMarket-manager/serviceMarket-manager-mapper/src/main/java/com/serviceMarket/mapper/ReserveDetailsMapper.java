package com.serviceMarket.mapper;

import com.serviceMarket.pojo.ReserveDetails;
import com.serviceMarket.pojo.ReserveDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReserveDetailsMapper {
    int countByExample(ReserveDetailsExample example);

    int deleteByExample(ReserveDetailsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReserveDetails record);

    int insertSelective(ReserveDetails record);

    List<ReserveDetails> selectByExample(ReserveDetailsExample example);

    ReserveDetails selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReserveDetails record, @Param("example") ReserveDetailsExample example);

    int updateByExample(@Param("record") ReserveDetails record, @Param("example") ReserveDetailsExample example);

    int updateByPrimaryKeySelective(ReserveDetails record);

    int updateByPrimaryKey(ReserveDetails record);
}