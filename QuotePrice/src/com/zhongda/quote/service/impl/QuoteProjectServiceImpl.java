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
import com.zhongda.quote.service.QuoteProjectService;
import com.zhongda.quote.utils.MyBatisUtil;

/**
 *
 * <p>
 * 报价项目Service类
 * </p>
 *
 * @author 研发中心-LiVerson<1061734892@qq.com>
 * @sine 2017年8月15日
 */
public class QuoteProjectServiceImpl implements QuoteProjectService {

	private static Logger logger = Logger
			.getLogger(QuoteProjectServiceImpl.class);

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
	public List<QuoteProject> queryAllQuoteProjectsByTaskNmber(int taskId) {
		List<QuoteProject> projectList = null;
		try {
			projectList = quoteProjectMapper.selectByTaskNumber(taskId);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			MyBatisUtil.closeSqlSession();
		}

		return projectList;
	}

	@Override
	public QuoteProject createProject(QuoteProject quoteProject) {
		int index = 0;
		try {
			index = quoteProjectMapper.insertSelective(quoteProject);
			sqlSession.commit();
			if (index > 0) {
				quoteProject = quoteProjectMapper.selectMaxProjectId();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			sqlSession.rollback();
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return quoteProject;
	}

	public boolean deleteQuoteProject(Integer id, double taskAmount) {
		int index = 0;
		try {
			QuoteProject quoteProject = quoteProjectMapper.selectByPrimaryKey(id);
			quoteTaskMapper.updateByPrimaryKeySelective(new QuoteTask(quoteProject.getTaskId(), taskAmount));
			index = quoteProjectMapper.deleteByPrimaryKey(id);
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

	public Map<String, Object> createProjectAndBatchAndContent(QuoteProject quoteProject,
			Map<String, Map<String, Object>> batchMap, double taskAmount) {
		Map<String, Object> quoteMap = null;
		try {
			quoteMap =new HashMap<String, Object>();
			int indexProject = quoteProjectMapper.insertSelective(quoteProject);
			if (indexProject > 0) {
				quoteProject = quoteProjectMapper.selectMaxProjectId();
				quoteMap.put("project", quoteProject);
				for (Map<String, Object> map : batchMap.values()) {
					InspectionBatch inspectionBatch = (InspectionBatch) map.get("batch");
					if(null != inspectionBatch){
						inspectionBatch.setProjectId(quoteProject.getId());
						int indexBatch = inspectionBatchMapper.insert(inspectionBatch);
						if(indexBatch >0){
							@SuppressWarnings("unchecked")
							List<InspectionContent> contentList = (List<InspectionContent>) map.get("content");
							if(null != contentList && contentList.size() > 0){
								inspectionBatch = inspectionBatchMapper.selectInspectionBatchByMaxId();
								for (InspectionContent inspectionContent : contentList) {
									inspectionContent.setBatchId(inspectionBatch.getId());
									inspectionContentMapper.insert(inspectionContent);
								}
								//如果有检验内容添加则修改任务金额
								quoteTaskMapper.updateByPrimaryKeySelective(new QuoteTask(quoteProject.getTaskId(), taskAmount));
							}
						}
					}
				}
				List<InspectionBatch> batchList = inspectionBatchMapper.selectByProjectNumber(quoteProject.getId());
				quoteMap.put("batch", batchList);
				if(null != batchList && batchList.size() > 0){
					InspectionBatch inspectionBatch = batchList.get(0);
					List<InspectionContent> contentList = inspectionContentMapper.selectAllInspectionContentByBatchId(inspectionBatch.getId());
					quoteMap.put("content", contentList);
				}
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

	public QuoteProject updateProject(QuoteProject quoteProject, QuoteTask quoteTask) {
		int index = 0;
		try {
			index = quoteProjectMapper.updateByPrimaryKeySelective(quoteProject);
			quoteTaskMapper.updateByPrimaryKeySelective(quoteTask);
			sqlSession.commit();
			if (index > 0) {
				quoteProject = quoteProjectMapper.selectByPrimaryKey(quoteProject.getId());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			sqlSession.rollback();
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return quoteProject;
	}

	public List<QuoteProject> queryProjectByPidAndName(Integer taskId, String projectName) {
		List<QuoteProject> projectList = null;
		try {
			if("".equals(projectName)){
				projectList = quoteProjectMapper.selectByTaskNumber(taskId);
			}else{
				projectList = quoteProjectMapper.selectProjectByPidAndName(taskId, projectName);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return projectList;
	}

}
