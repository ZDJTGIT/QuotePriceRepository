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
	 * @param taskAmount
	 *            需修改的任务金额
	 * @param projectAmount
	 *            需修改的项目金额
	 * @param batchAmount
	 *            需修改的检验批金额
	 * @return
	 */
	InspectionContent createInspectionContent(
			InspectionContent inspectionContent, double taskAmount,
			double projectAmount, double batchAmount);

	/**
	 * 获取当前检验批下所有的检验内容
	 *
	 * @param batchId
	 *            当前检验批的id
	 * @return 当前检验批下所有的检验内容
	 */
	List<InspectionContent> queryAllInspectionContentByBatchId(Integer batchId);

	/**
	 * 
	 * 查询所有关联抽样依据和报价依据表的检测内容根据检验批ID
	 * 
	 * @param batchId
	 *            检验批ID
	 * @return 检验内容集合
	 */
	List<InspectionContent> queryAllContentByBatchId(int batchId);

	/**
	 * 按ID删除检验内容,同时修改任务，项目以及检验批金额
	 * 
	 * @param id
	 * @return
	 */
	Integer deleteInspectionByID(Integer id, double taskAmount,
			double projectAmount, double batchAmount);

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
	 * 
	 * @param InspectionContentID
	 * @return InspectionContent
	 */
	InspectionContent selectInspectionContentByInspectionContentID(
			Integer InspectionContentID);
	/**
	 * 根据选中的检验批和输入关键字查询所有符合条件的检验内容
	 * @param batchid
	 * @param ContentName
	 * @return
	 */
	List<InspectionContent>selectByBatchidAndContentName(Integer batchid,
			String ContentName);
}
