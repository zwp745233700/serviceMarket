package com.serviceMarket.mapper;

import com.serviceMarket.pojo.HelperTable;
import com.serviceMarket.pojo.HelperTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HelperTableMapper {
    int countByExample(HelperTableExample example);

    int deleteByExample(HelperTableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HelperTable record);

    int insertSelective(HelperTable record);

    List<HelperTable> selectByExample(HelperTableExample example);

    HelperTable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HelperTable record, @Param("example") HelperTableExample example);

    int updateByExample(@Param("record") HelperTable record, @Param("example") HelperTableExample example);

    int updateByPrimaryKeySelective(HelperTable record);

    int updateByPrimaryKey(HelperTable record);
}