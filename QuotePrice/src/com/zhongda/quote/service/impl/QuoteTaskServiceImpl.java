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
 *<p>报价任务service类</p>
 * @author zmdeng
 * @date 2017年8月10日
 */
public class QuoteTaskServiceImpl implements QuoteTaskService {

	private static Logger logger = Logger.getLogger(QuoteTaskServiceImpl.class);

	private SqlSession sqlSession = MyBatisUtil.getSqlSession();
	private QuoteTaskMapper quoteTaskMapper = sqlSession.getMapper(QuoteTaskMapper.class);

	@Override
	public String createQuoteTask(QuoteTask quoteTask) {
		//流水号工具类
		PrimaryGeneraterUtil primaryGeneraterUtil = null;
		String nextNumber = null;
		int index = 0;
		try {
			primaryGeneraterUtil = PrimaryGeneraterUtil.getInstance();
			//获得下一个流水号
			nextNumber = primaryGeneraterUtil.getNextNumber();
			quoteTask.setTaskNumber(nextNumber);
			//插入数据库的操作
			index = quoteTaskMapper.insertSelective(quoteTask);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			sqlSession.rollback();
		}finally{
			MyBatisUtil.closeSqlSession();
		}
		if(index<1){
			return "创建报价任务失败，请稍后重试！";
		}else{
			//保存流水号
			primaryGeneraterUtil.saveNextNumber(nextNumber);
			return "创建报价任务成功！";
		}
	}

	@Override
	public String deleteQuoteTask(Integer id) {
		int index = 0;
		try {
			index = quoteTaskMapper.deleteByPrimaryKey(id);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			sqlSession.rollback();
		}finally{
			MyBatisUtil.closeSqlSession();
		}
		if(index<1){
			return "删除报价任务失败，请稍后重试！";
		}else{
			return "删除报价任务成功！";
		}
	}

	@Override
	public String updateQuoteTask(QuoteTask quoteTask) {
		int index = 0;
		try {
			index = quoteTaskMapper.updateByPrimaryKeySelective(quoteTask);
			sqlSession.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			sqlSession.rollback();
		}finally{
			MyBatisUtil.closeSqlSession();
		}
		if(index<1){
			return "更新报价任务失败，请稍后重试！";
		}else{
			return "更新报价任务成功！";
		}
	}

	@Override
	public List<QuoteTask> queryQuoteTaskByName(String taskName) {
		List<QuoteTask> taskList = null;
		try{
			taskList = quoteTaskMapper.selectByName(taskName);
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			MyBatisUtil.closeSqlSession();
		}
		return taskList;
	}

	@Override
	public List<QuoteTask> queryAllQuoteTask() {
		List<QuoteTask> taskList = null;
		try{
			taskList = quoteTaskMapper.selectAll();
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally{
			MyBatisUtil.closeSqlSession();
		}
		return taskList;
	}

}
