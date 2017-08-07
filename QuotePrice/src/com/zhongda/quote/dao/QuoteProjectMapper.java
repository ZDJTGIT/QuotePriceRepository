package com.zhongda.quote.dao;

import com.zhongda.quote.model.QuoteProject;

public interface QuoteProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuoteProject record);

    int insertSelective(QuoteProject record);

    QuoteProject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuoteProject record);

    int updateByPrimaryKey(QuoteProject record);
}