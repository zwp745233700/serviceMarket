package com.serviceMarket.mapper;

import com.serviceMarket.pojo.GroupDetails;
import com.serviceMarket.pojo.GroupDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupDetailsMapper {
    int countByExample(GroupDetailsExample example);

    int deleteByExample(GroupDetailsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GroupDetails record);

    int insertSelective(GroupDetails record);

    List<GroupDetails> selectByExample(GroupDetailsExample example);

    GroupDetails selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GroupDetails record, @Param("example") GroupDetailsExample example);

    int updateByExample(@Param("record") GroupDetails record, @Param("example") GroupDetailsExample example);

    int updateByPrimaryKeySelective(GroupDetails record);

    int updateByPrimaryKey(GroupDetails record);
}