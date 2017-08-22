package com.zhongda.quote.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.zhongda.quote.dao.InspectionContentMapper;
import com.zhongda.quote.model.InspectionContent;
import com.zhongda.quote.service.InspectionContentService;
import com.zhongda.quote.utils.MyBatisUtil;

public class InspectionContentServiceImpl implements InspectionContentService {

	private static Logger logger = Logger
			.getLogger(InspectionContentServiceImpl.class);

	private SqlSession sqlSession = MyBatisUtil.getSqlSession();

	private InspectionContentMapper inspectionContentMapper = MyBatisUtil
			.getSqlSession().getMapper(InspectionContentMapper.class);

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

	//插入一天检验内容，如果插入成功，返回当前插入的检验内容
	@Override
	public InspectionContent createInspectionContent(
			InspectionContent inspectionContent) {
		try {
			int index = inspectionContentMapper
					.insertSelective(inspectionContent);
			sqlSession.commit();
			if (index > 0) {
				//添加实时更新原理
				inspectionContent = inspectionContentMapper
						.selectInspectionContentByMaxId();
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
	public Integer deleteInspectionByID(Integer id) {
		int index = 0;
		try {
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

}
