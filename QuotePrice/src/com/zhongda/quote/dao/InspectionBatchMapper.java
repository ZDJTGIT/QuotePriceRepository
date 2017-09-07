package com.zhongda.quote.dao;

import java.util.List;

import com.zhongda.quote.model.InspectionBatch;

public interface InspectionBatchMapper {

	/**
	 * 删除指定id的检验批
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 插入一条检验批
	 * @param record
	 * @return
	 */
	int insert(InspectionBatch record);

	int insertSelective(InspectionBatch record);

	InspectionBatch selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(InspectionBatch record);

	int updateByPrimaryKey(InspectionBatch record);

	/**
	 * 查询检验批通过项目ID
	 *
	 * @return
	 */
	List<InspectionBatch> selectByProjectNumber(int id);

	/**
	 * 查询最大Id的检验批
	 *
	 * @param maxId
	 * @return
	 */
	InspectionBatch selectInspectionBatchByMaxId();

	/**
	 * 查询检验批通过项目id和检验批名称
	 * @param projectId
	 * @param batchName
	 * @return
	 */
	List<InspectionBatch> selectProjectByPidAndName(Integer projectId,
			String batchName);
}