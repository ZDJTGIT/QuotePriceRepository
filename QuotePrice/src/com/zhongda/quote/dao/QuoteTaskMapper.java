package com.zhongda.quote.dao;

import com.zhongda.quote.model.QuoteTask;

public interface QuoteTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuoteTask record);

    int insertSelective(QuoteTask record);

    QuoteTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuoteTask record);

    int updateByPrimaryKey(QuoteTask record);
}