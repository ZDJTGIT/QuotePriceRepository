package com.zhongda.quote.dao;

import com.zhongda.quote.model.SampleBasis;

public interface SampleBasisMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SampleBasis record);

    int insertSelective(SampleBasis record);

    SampleBasis selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SampleBasis record);

    int updateByPrimaryKey(SampleBasis record);
}