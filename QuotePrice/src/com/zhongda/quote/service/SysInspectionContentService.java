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
}
