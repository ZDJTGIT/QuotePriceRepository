package com.zhongda.quote.service;

import java.util.List;

import com.zhongda.quote.model.InspectionBatch;
import com.zhongda.quote.model.InspectionContent;
import com.zhongda.quote.model.QuoteProject;
import com.zhongda.quote.model.QuoteTask;

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
	 * 修改检验内容的抽样数量以及单个对象实施数量时，同时修改任务，项目，检验批的金额
	 * @param quoteTask
	 * @param quoteProject
	 * @param inspectionBatch
	 * @param inspectionContent
	 * @return
	 */
	InspectionContent updateInspectionContent(QuoteTask quoteTask, QuoteProject quoteProject,
			InspectionBatch inspectionBatch, InspectionContent inspectionContent);

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
	InspectionContent selectInspectionContentById(
			Integer InspectionContentID);

	/**
	 * 查询检验内容根据检验批id和检验内容名称
	 * @param batchId
	 * @param contentName
	 * @return
	 */
	List<InspectionContent> queryContentByPidAndName(Integer batchId, String contentName);
}
