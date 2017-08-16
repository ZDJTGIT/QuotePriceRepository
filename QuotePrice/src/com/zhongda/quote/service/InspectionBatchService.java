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
}
