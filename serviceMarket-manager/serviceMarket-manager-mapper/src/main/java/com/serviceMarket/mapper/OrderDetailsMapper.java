package com.serviceMarket.mapper;

import com.serviceMarket.pojo.OrderDetails;
import com.serviceMarket.pojo.OrderDetailsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderDetailsMapper {
    int countByExample(OrderDetailsExample example);

    int deleteByExample(OrderDetailsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderDetails record);

    int insertSelective(OrderDetails record);

    List<OrderDetails> selectByExample(OrderDetailsExample example);

    OrderDetails selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderDetails record, @Param("example") OrderDetailsExample example);

    int updateByExample(@Param("record") OrderDetails record, @Param("example") OrderDetailsExample example);

    int updateByPrimaryKeySelective(OrderDetails record);

    int updateByPrimaryKey(OrderDetails record);
}