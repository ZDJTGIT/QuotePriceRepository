package com.zhongda.quote.dao;

import java.util.List;

import com.zhongda.quote.model.Industry;

public interface IndustryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Industry record);

    int insertSelective(Industry record);

    Industry selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Industry record);

    int updateByPrimaryKey(Industry record);

    /**
     *	查询出所有的行业
     * @return
     */
    List<Industry> selectAll();
}