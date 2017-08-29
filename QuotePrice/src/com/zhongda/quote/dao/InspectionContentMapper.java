package com.zhongda.quote.dao;

import java.util.List;

import com.zhongda.quote.model.InspectionContent;

public interface InspectionContentMapper {

	int deleteByPrimaryKey(Integer id);

	/*
	 * 插入一条检验内容
	 * 
	 * @return
	 */
	int insertSelective(InspectionContent record);

	/**
	 * 依据ID查询检验内容
	 *
	 * @return
	 */
	InspectionContent selectByPrimaryKey(Integer id);

	/**
	 * 修改检验内容
	 *
	 * @param inspectionContent
	 *            被修改的检验内容
	 * @return
	 */
	int updateByPrimaryKeySelective(InspectionContent record);

	int updateByPrimaryKey(InspectionContent record);

	/**
	 * 查询所有的检验内容
	 *
	 * @return
	 */
	List<InspectionContent> selectAllInspectionContent();

	/**
	 * 查询出id最大的检验内容
	 *
	 * @return
	 */
	InspectionContent selectInspectionContentByMaxId();

	/**
	 *
	 * 按ID删除检验内容
	 *
	 * @return
	 */
	int deleteInspectionByID(Integer id);

	/**
	 * 查询所有检测内容根据检验批ID
	 *
	 * @param batchId
	 *            当前检验批的id
	 * @return 当前检验批下所有的检验内容
	 */
	List<InspectionContent> selectAllInspectionContentByBatchId(Integer batchId);

	/**
	 * 插入一条检验内容
	 * 
	 * @param record
	 * @return
	 */
	int insert(InspectionContent record);
	
	/**
	 * 传当前检验内容ID获取检验内容
	 * @param InspectionContentID
	 * @return InspectionContent
	 */
	InspectionContent selectInspectionContentByInspectionContentID(Integer InspectionContentID);

	/**
	 * 查询所有关联抽样依据和报价依据表的检测内容根据检验批ID
	 * 
	 * @param batchId
	 *            检验批ID
	 * @return 检验内容集合
	 */
	List<InspectionContent> selectAllContentByBatchId(int batchId);

}