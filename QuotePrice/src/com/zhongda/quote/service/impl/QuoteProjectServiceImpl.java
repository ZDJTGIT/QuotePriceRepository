package com.zhongda.quote.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.zhongda.quote.dao.QuoteProjectMapper;
import com.zhongda.quote.model.QuoteProject;
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

	private QuoteProjectMapper quoteProjectMapper = sqlSession
			.getMapper(QuoteProjectMapper.class);

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
		QuoteProject quoteProjectNew = null;
		int index = 0;
		try {
			index = quoteProjectMapper.insertSelective(quoteProject);
			sqlSession.commit();
			if (index > 0) {
				quoteProjectNew = quoteProjectMapper.selectMaxProjectId();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			sqlSession.rollback();
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return quoteProjectNew;
	}

}
