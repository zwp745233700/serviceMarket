package com.serviceMarket.mapper;

import com.serviceMarket.pojo.Token;
import com.serviceMarket.pojo.TokenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TokenMapper {
    int countByExample(TokenExample example);

    int deleteByExample(TokenExample example);

    int deleteByPrimaryKey(String id);

    int insert(Token record);

    int insertSelective(Token record);

    List<Token> selectByExample(TokenExample example);

    Token selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Token record, @Param("example") TokenExample example);

    int updateByExample(@Param("record") Token record, @Param("example") TokenExample example);

    int updateByPrimaryKeySelective(Token record);

    int updateByPrimaryKey(Token record);
}