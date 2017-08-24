package com.zhongda.quote.service;

import java.util.List;

import com.zhongda.quote.model.InspectionContent;

public interface InspectionContentService {

	/**
	 * 获取所有的检验内容
	 *
	 * @return
	 */
	List<InspectionContent> queryAllInspectionContent();

	/**
	 * 创建一个检验内容
	 *
	 * @param inspectionContent
	 * @return 返回当前插入检验内容，可通过返回的任务是否为null来判断创建是否成功
	 */
	InspectionContent createInspectionContent(
			InspectionContent inspectionContent);

	/**
	 * 获取当前检验批下所有的检验内容
	 *
	 * @param batchId
	 *            当前检验批的id
	 * @return 当前检验批下所有的检验内容
	 */
	List<InspectionContent> queryAllInspectionContentByBatchId(Integer batchId);

	/**
	 * 按ID删除检验内容,同时修改任务，项目以及检验批金额
	 * @param id
	 * @return
	 */
	Integer deleteInspectionByID(Integer id, double taskAmount, double projectAmount, double batchAmount);

	/**
	 * 修改检验内容
	 *
	 * @param inspectionContent
	 *            被修改的检验内容
	 * @return 修改后的检验内容
	 */
	InspectionContent updateInspectionContent(
			InspectionContent inspectionContent);

	/**
	 * 插入用户自定义检验内容
	 *
	 * @param inspectionContent
	 * @return
	 */
	boolean insertMultipleInspectionContent(
			List<InspectionContent> inspectionContent);
	/**
	 * 传当前检验内容ID获取检验内容
	 * @param InspectionContentID
	 * @return InspectionContent
	 */
	InspectionContent selectInspectionContentByInspectionContentID(Integer InspectionContentID);
}
