package com.zhongda.quote.dao;

import com.zhongda.quote.model.QuoteBasis;

public interface QuoteBasisMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuoteBasis record);

    int insertSelective(QuoteBasis record);

    QuoteBasis selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuoteBasis record);

    int updateByPrimaryKey(QuoteBasis record);
}