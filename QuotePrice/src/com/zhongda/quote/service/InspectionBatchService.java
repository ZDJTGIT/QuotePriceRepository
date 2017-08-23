package com.zhongda.quote.service;

import java.util.List;

import com.zhongda.quote.model.InspectionBatch;

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
	 * 删除指定id的检验批
	 * @param id
	 * @return
	 */
	boolean deleteInspectionBatch(Integer id);
}
