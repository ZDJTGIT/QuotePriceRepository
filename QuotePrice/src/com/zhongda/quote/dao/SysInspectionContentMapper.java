package com.zhongda.quote.dao;

import com.zhongda.quote.model.SysInspectionContent;

public interface SysInspectionContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysInspectionContent record);

    int insertSelective(SysInspectionContent record);

    SysInspectionContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysInspectionContent record);

    int updateByPrimaryKey(SysInspectionContent record);
}