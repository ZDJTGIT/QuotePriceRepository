package com.zhongda.quote.dao;

import com.zhongda.quote.model.InspectionContent;

public interface InspectionContentMapper {


	int deleteByPrimaryKey(Integer id);

    int insert(InspectionContent record);

    int insertSelective(InspectionContent record);

    InspectionContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InspectionContent record);

    int updateByPrimaryKey(InspectionContent record);
}