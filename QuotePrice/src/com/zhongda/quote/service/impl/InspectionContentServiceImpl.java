package com.zhongda.quote.service.impl;

import java.util.List;

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
import com.zhongda.quote.service.InspectionContentService;
import com.zhongda.quote.utils.MyBatisUtil;

public class InspectionContentServiceImpl implements InspectionContentService {

	private static Logger logger = Logger
			.getLogger(InspectionContentServiceImpl.class);

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
	public List<InspectionContent> queryAllInspectionContent() {
		List<InspectionContent> contentList = null;
		try {
			contentList = inspectionContentMapper.selectAllInspectionContent();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return contentList;
	}

	// 插入一天检验内容，如果插入成功，返回当前插入的检验内容
	@Override
	public InspectionContent createInspectionContent(
			InspectionContent inspectionContent, double taskAmount,
			double projectAmount, double batchAmount) {
		int index = 0;
		try {
			index = inspectionContentMapper.insertSelective(inspectionContent);
			if (index > 0) {

				inspectionBatchMapper
						.updateByPrimaryKeySelective(new InspectionBatch(
								inspectionContent.getBatchId(), batchAmount));
				InspectionBatch inspectionBatch = inspectionBatchMapper
						.selectByPrimaryKey(inspectionContent.getBatchId());
				quoteProjectMapper
						.updateByPrimaryKeySelective(new QuoteProject(
								inspectionBatch.getProjectId(), projectAmount));
				QuoteProject quoteProject = quoteProjectMapper
						.selectByPrimaryKey(inspectionBatch.getProjectId());
				quoteTaskMapper.updateByPrimaryKeySelective(new QuoteTask(
						quoteProject.getTaskId(), taskAmount));
				inspectionContent = inspectionContentMapper
						.selectInspectionContentByMaxId();
			} else {
				inspectionContent = null;

				// 添加实时更新原理
				inspectionContent = inspectionContentMapper
						.selectInspectionContentByMaxId();

			}
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			sqlSession.rollback();
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return inspectionContent;
	}

	@Override
	public Integer deleteInspectionByID(Integer id, double taskAmount,
			double projectAmount, double batchAmount) {
		int index = 0;
		try {
			InspectionContent inspectionContent = inspectionContentMapper
					.selectByPrimaryKey(id);
			inspectionBatchMapper
					.updateByPrimaryKeySelective(new InspectionBatch(
							inspectionContent.getBatchId(), batchAmount));
			InspectionBatch inspectionBatch = inspectionBatchMapper
					.selectByPrimaryKey(inspectionContent.getBatchId());
			quoteProjectMapper.updateByPrimaryKeySelective(new QuoteProject(
					inspectionBatch.getProjectId(), projectAmount));
			QuoteProject quoteProject = quoteProjectMapper
					.selectByPrimaryKey(inspectionBatch.getProjectId());
			quoteTaskMapper.updateByPrimaryKeySelective(new QuoteTask(
					quoteProject.getTaskId(), taskAmount));
			index = inspectionContentMapper.deleteInspectionByID(id);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			sqlSession.rollback();
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return index;
	}

	public List<InspectionContent> queryAllInspectionContentByBatchId(
			Integer batchId) {
		List<InspectionContent> inspectionContentList = null;
		try {
			inspectionContentList = inspectionContentMapper
					.selectAllInspectionContentByBatchId(batchId);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return inspectionContentList;
	}

	public InspectionContent updateInspectionContent(
			InspectionContent inspectionContent) {
		int index = 0;
		try {
			index = inspectionContentMapper
					.updateByPrimaryKeySelective(inspectionContent);
			sqlSession.commit();
			if (index > 0) {
				inspectionContent = inspectionContentMapper
						.selectByPrimaryKey(inspectionContent.getId());
			} else {
				inspectionContent = null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			sqlSession.rollback();
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return inspectionContent;
	}

	@Override
	public boolean insertMultipleInspectionContent(
			List<InspectionContent> inspectionContent) {
		boolean isTrue = true;
		int index = 0;
		try {
			for (InspectionContent inspectionContent2 : inspectionContent) {
				index = inspectionContentMapper
						.insertSelective(inspectionContent2);
				if (index < 0) {
					isTrue = false;
					break;
				}
				sqlSession.commit();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			sqlSession.rollback();
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return isTrue;
	}

	@Override
	public InspectionContent selectInspectionContentByInspectionContentID(
			Integer InspectionContentID) {
		InspectionContent inspectionContent = null;
		try {
			inspectionContent = inspectionContentMapper
					.selectInspectionContentByInspectionContentID(InspectionContentID);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return inspectionContent;
	}

	@Override
	public List<InspectionContent> queryAllContentByBatchId(int batchId) {
		List<InspectionContent> contentList = null;
		try {
			contentList = inspectionContentMapper
					.selectAllContentByBatchId(batchId);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return contentList;
	}
}