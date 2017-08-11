package com.zhongda.quote.dao;

import com.zhongda.quote.model.InspectionBatch;

public interface InspectionBatchMapper {


	int deleteByPrimaryKey(Integer id);

    int insert(InspectionBatch record);

    int insertSelective(InspectionBatch record);

    InspectionBatch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InspectionBatch record);

    int updateByPrimaryKey(InspectionBatch record);
}