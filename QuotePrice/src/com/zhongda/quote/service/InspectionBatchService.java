package com.zhongda.quote.service;

import java.util.List;
import java.util.Map;

import com.zhongda.quote.model.InspectionBatch;
import com.zhongda.quote.model.InspectionContent;

public interface InspectionBatchService {

	/**
	 * 查询所有检验批通过项目id
	 *
	 * @param id
	 *
	 * @return
	 */
	List<InspectionBatch> queryAllInspectionBatchByProjectID(int id);

	/**
	 * 查询Id最大的检验批
	 *
	 * @param inspectionBatch
	 * @return
	 */
	InspectionBatch queryInspectionBatchByMaxId(InspectionBatch inspectionBatch);

	/**
	 * 删除指定id的检验批，同时修改项目和任务金额
	 * @param id
	 * @return
	 */
	boolean deleteInspectionBatch(Integer id, double taskAmount, double projectAmount);

	/**
	 *  ** 联动插入一条检验批同时插入对应的检验内容
	 * @param inspectionBatch 需插入的检验批对象
	 * @param singleContentList  需插入的检验内容
	 * @param taskAmount 需修改的任务金额
	 * @param projectAmount 需修改的项目金额
	 * @return 插入成功的所有数据
	 */
	Map<String, Object> createBatchAndContent(
			InspectionBatch inspectionBatch,
			List<InspectionContent> singleContentList, double taskAmount, double projectAmount);

	/**
	 * 修改检验批
	 * @param inspectionBatch 新的检验批
	 * @return 返回新的检验批
	 */
	InspectionBatch updateBatch(InspectionBatch inspectionBatch);

	/**
	 * 查询检验批通过项目id和检验批名称
	 * @param projectId
	 * @param batchName
	 * @return
	 */
	List<InspectionBatch> queryBatchByPidAndName(Integer projectId, String batchName);
}
