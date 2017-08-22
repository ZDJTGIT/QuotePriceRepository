package com.zhongda.quote.service;

import java.util.List;

import com.zhongda.quote.model.SysInspectionContent;

public interface SysInspectionContentService {

	/**
	 * 查询系统检测内容通过内容名称
	 * 
	 * @param contentName
	 * @return
	 */
	List<SysInspectionContent> querySysInspectionContentByContentName(
			String contentName);
	
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
}
