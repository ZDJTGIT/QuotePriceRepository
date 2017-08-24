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
	 * 根据检验内容名称模糊查询 以及所选行业，地址
	 * @param name 内容名称
	 * @param addressId 地址id
	 * @param industryId 行业id
	 * @return
	 */
	List<SysInspectionContent> selectByName(String name, int industryId, int addressId);

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
	/**
	 * 传入当前选中的检验内容ID查询系统检验内容表
	 * @param InspectionContentID
	 * @return SysInspectionContent
	 */
	SysInspectionContent selectSysInspectionContentByInspectionContentID(Integer InspectionContentID);
}