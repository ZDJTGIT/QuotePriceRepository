package com.zhongda.quote.service;

import java.util.List;

import com.zhongda.quote.model.SysInspectionContent;

public interface SysInspectionContentService {

	/**
	 * 查询系统检测内容通过内容名称以及所选行业和地址
	 * @param contentName 内容名称
	 * @param industryId 行业id
	 * @param addressId 地址id
	 * @return
	 */
	List<SysInspectionContent> querySysInspectionContentByContentName(
			String contentName, int industryId, int addressId);

	/**
	 * 查询所有未添加的系统检测内容
	 * @return
	 */
	List<SysInspectionContent> selectSysInspectionContent(Integer batchID);

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
