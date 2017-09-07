package com.zhongda.quote.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.zhongda.quote.dao.InspectionBatchMapper;
import com.zhongda.quote.dao.InspectionContentMapper;
import com.zhongda.quote.dao.QuoteProjectMapper;
import com.zhongda.quote.dao.QuoteTaskMapper;
import com.zhongda.quote.model.InspectionBatch;
import com.zhongda.quote.model.InspectionContent;
import com.zhongda.quote.model.QuoteProject;
import com.zhongda.quote.model.QuoteTask;
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

	private SqlSession sqlSession = MyBatisUtil.getSqlSession();
	private QuoteTaskMapper quoteTaskMapper = sqlSession
			.getMapper(QuoteTaskMapper.class);
	private QuoteProjectMapper quoteProjectMapper = sqlSession
			.getMapper(QuoteProjectMapper.class);
	private InspectionBatchMapper inspectionBatchMapper = sqlSession
			.getMapper(InspectionBatchMapper.class);
	private InspectionContentMapper inspectionContentMapper = sqlSession
			.getMapper(InspectionContentMapper.class);

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

	@Override
	public InspectionBatch queryInspectionBatchByMaxId(
			InspectionBatch inspectionBatch) {
		InspectionBatch insBatch = null;
		int index = 0;
		try {
			index = inspectionBatchMapper.insertSelective(inspectionBatch);
			sqlSession.commit();
			if (index > 0) {
				insBatch = inspectionBatchMapper.selectInspectionBatchByMaxId();
			} else {
				insBatch = null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			sqlSession.rollback();
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return insBatch;
	}

	public boolean deleteInspectionBatch(Integer id, double taskAmount, double projectAmount) {
		int index = 0;
		try {
			InspectionBatch inspectionBatch = inspectionBatchMapper.selectByPrimaryKey(id);
			quoteProjectMapper.updateByPrimaryKeySelective(new QuoteProject(inspectionBatch.getProjectId(), projectAmount));
			QuoteProject quoteProject = quoteProjectMapper.selectByPrimaryKey(inspectionBatch.getProjectId());
			quoteTaskMapper.updateByPrimaryKeySelective(new QuoteTask(quoteProject.getTaskId(), taskAmount));
			index = inspectionBatchMapper.deleteByPrimaryKey(id);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			sqlSession.rollback();
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		if (index < 1) {
			return false;
		} else {
			return true;
		}
	}

	public Map<String, Object> createBatchAndContent(
			InspectionBatch inspectionBatch,
			List<InspectionContent> singleContentList, double taskAmount, double projectAmount) {
		Map<String, Object> quoteMap = null;
		try {
			quoteMap =new HashMap<String, Object>();
			int indexBatch = inspectionBatchMapper.insertSelective(inspectionBatch);
			if (indexBatch > 0) {
				inspectionBatch = inspectionBatchMapper.selectInspectionBatchByMaxId();
				quoteMap.put("batch", inspectionBatch);
				if(null != singleContentList && singleContentList.size() > 0){
					for (InspectionContent inspectionContent : singleContentList) {
						inspectionContent.setBatchId(inspectionBatch.getId());
						inspectionContentMapper.insert(inspectionContent);
					}
					//修改项目金额
					QuoteProject quoteProject = quoteProjectMapper.selectByPrimaryKey(inspectionBatch.getProjectId());
					quoteProjectMapper.updateByPrimaryKeySelective(new QuoteProject(quoteProject.getId(), projectAmount));
					//修改任务金额
					quoteTaskMapper.updateByPrimaryKeySelective(new QuoteTask(quoteProject.getTaskId(), taskAmount));
				}
				List<InspectionContent> contentList = inspectionContentMapper.selectAllInspectionContentByBatchId(inspectionBatch.getId());
				quoteMap.put("content", contentList);
			}
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			sqlSession.rollback();
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return quoteMap;
	}

	public InspectionBatch updateBatch(InspectionBatch inspectionBatch) {
		int index = 0;
		try {
			index = inspectionBatchMapper.updateByPrimaryKeySelective(inspectionBatch);
			sqlSession.commit();
			if (index > 0) {
				inspectionBatch = inspectionBatchMapper.selectByPrimaryKey(inspectionBatch.getId());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			sqlSession.rollback();
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return inspectionBatch;
	}

	public List<InspectionBatch> queryBatchByPidAndName(Integer projectId, String batchName) {
		List<InspectionBatch> batchList = null;
		try {
			if("".equals(batchName)){
				batchList = inspectionBatchMapper.selectByProjectNumber(projectId);
			}else{
				batchList = inspectionBatchMapper.selectProjectByPidAndName(projectId, batchName);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return batchList;
	}
}
