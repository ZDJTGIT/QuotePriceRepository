package com.zhongda.quote.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.zhongda.quote.dao.InspectionBatchMapper;
import com.zhongda.quote.model.InspectionBatch;
import com.zhongda.quote.service.InspectionBatchService;
import com.zhongda.quote.utils.MyBatisUtil;

/**
 * 
 * <p>
 * 检验批Service类
 * </p>
 * 
 * @author 研发中心-LiVerson<1061734892@qq.com>
 * @sine 2017年8月16日
 */
public class InspectionBatchServiceImpl implements InspectionBatchService {

	private static Logger logger = Logger
			.getLogger(InspectionBatchServiceImpl.class);

	private InspectionBatchMapper inspectionBatchMapper = MyBatisUtil
			.getSqlSession().getMapper(InspectionBatchMapper.class);

	@Override
	public List<InspectionBatch> queryAllInspectionBatchByProjectID(int id) {
		List<InspectionBatch> batchList = null;
		try {
			batchList = inspectionBatchMapper.selectByProjectNumber(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			MyBatisUtil.closeSqlSession();
		}

		return batchList;
	}

}
