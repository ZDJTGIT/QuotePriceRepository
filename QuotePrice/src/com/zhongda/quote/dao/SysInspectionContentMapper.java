package com.zhongda.quote.dao;

import java.util.List;

import com.zhongda.quote.model.SysInspectionContent;

public interface SysInspectionContentMapper {
	
	int deleteByPrimaryKey(Integer id);

	int insert(SysInspectionContent record);

	int insertSelective(SysInspectionContent record);

	SysInspectionContent selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SysInspectionContent record);

	int updateByPrimaryKey(SysInspectionContent record);

	/**
	 * 根据检验内容名称模糊查询
	 * 
	 * @param name
	 * @return
	 */
	List<SysInspectionContent> selectByName(String name);
	
	/**
	 * 查询所有未添加的系统检验内容
	 * @return SysInspectionContent
	 */
	List<SysInspectionContent> selectAllSysInspectionContent(Integer batchID);
	
	/**
	 * 根据用户输入的关键字查询所有匹配的选项
	 * @param blurryString
	 * @return SysInspectionContent
	 */
	List<SysInspectionContent> selectAllBlurrySysInspectionContent(String blurryString);
}