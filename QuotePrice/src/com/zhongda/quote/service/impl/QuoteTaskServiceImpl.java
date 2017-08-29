package com.zhongda.quote.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.zhongda.quote.dao.QuoteTaskMapper;
import com.zhongda.quote.model.QuoteTask;
import com.zhongda.quote.service.QuoteTaskService;
import com.zhongda.quote.utils.MyBatisUtil;
import com.zhongda.quote.utils.PrimaryGeneraterUtil;

/**
 * <p>
 * 报价任务service类
 * </p>
 * 
 * @author zmdeng
 * @date 2017年8月10日
 */
public class QuoteTaskServiceImpl implements QuoteTaskService {

	private static Logger logger = Logger.getLogger(QuoteTaskServiceImpl.class);

	private SqlSession sqlSession = MyBatisUtil.getSqlSession();
	private QuoteTaskMapper quoteTaskMapper = sqlSession
			.getMapper(QuoteTaskMapper.class);

	@Override
	public QuoteTask createQuoteTask(QuoteTask quoteTask) {
		// 流水号工具类
		PrimaryGeneraterUtil primaryGeneraterUtil = null;
		String nextNumber = null;
		int index = 0;
		try {
			primaryGeneraterUtil = PrimaryGeneraterUtil.getInstance();
			// 获得下一个流水号
			nextNumber = primaryGeneraterUtil.getNextNumber();
			quoteTask.setTaskNumber(nextNumber);
			// 插入数据库的操作
			index = quoteTaskMapper.insertSelective(quoteTask);
			sqlSession.commit();
			// 插入数据成功
			if (index > 0) {
				// 保存流水号
				primaryGeneraterUtil.saveNextNumber(nextNumber);
				// 再查出该条数据
				quoteTask = quoteTaskMapper.selectByNumber(nextNumber);
			} else {
				quoteTask = null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			sqlSession.rollback();
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return quoteTask;
	}

	@Override
	public boolean deleteQuoteTask(Integer id) {
		int index = 0;
		try {
			index = quoteTaskMapper.deleteByPrimaryKey(id);
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

	@Override
	public QuoteTask updateQuoteTask(QuoteTask quoteTask) {
		int index = 0;
		try {
			index = quoteTaskMapper.updateByPrimaryKeySelective(quoteTask);
			if (index > 0) {
				// 再查出该条数据
				quoteTask = quoteTaskMapper.selectByPrimaryKey(quoteTask
						.getId());
			} else {
				quoteTask = null;
			}
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			sqlSession.rollback();
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return quoteTask;
	}

	@Override
	public List<QuoteTask> queryQuoteTaskByName(String taskName) {
		List<QuoteTask> taskList = null;
		try {
			taskList = quoteTaskMapper.selectByName(taskName);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return taskList;
	}

	@Override
	public List<QuoteTask> queryAllQuoteTask() {
		List<QuoteTask> taskList = null;
		try {
			taskList = quoteTaskMapper.selectAll();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return taskList;
	}

	@Override
	public QuoteTask queryQuoteTaskByNumber(String taskNumber) {
		QuoteTask quoteTask = null;
		try {
			quoteTask = quoteTaskMapper.selectByNumber(taskNumber);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return quoteTask;
	}

}
