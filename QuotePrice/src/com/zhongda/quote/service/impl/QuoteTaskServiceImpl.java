package com.zhongda.quote.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.zhongda.quote.dao.QuoteTaskMapper;
import com.zhongda.quote.model.QuoteTask;
import com.zhongda.quote.service.QuoteTaskService;
import com.zhongda.quote.utils.MyBatisUtil;
import com.zhongda.quote.utils.PrimaryGeneraterUtil;

public class QuoteTaskServiceImpl implements QuoteTaskService {

	private static Logger logger = Logger.getLogger(QuoteTaskServiceImpl.class);

	private SqlSession sqlSession = MyBatisUtil.getSqlSession();
	private QuoteTaskMapper quoteTaskMapper = sqlSession.getMapper(QuoteTaskMapper.class);

	@Override
	public String createQuoteTask(QuoteTask quoteTask) {
		PrimaryGeneraterUtil primaryGeneraterUtil = null;
		String nextNumber = null;
		int index = 0;
		try {
			System.out.println(sqlSession);
			primaryGeneraterUtil = PrimaryGeneraterUtil.getInstance();
			nextNumber = primaryGeneraterUtil.getNextNumber();
			quoteTask.setTaskNumber(nextNumber);
			index = quoteTaskMapper.insertSelective(quoteTask);
			sqlSession.commit();
			System.out.println(sqlSession);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}finally{
			sqlSession.close();
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
		int index = quoteTaskMapper.deleteByPrimaryKey(id);
		sqlSession.commit();
		sqlSession.close();
		if(index<1){
			return "删除报价任务失败，请稍后重试！";
		}else{
			return "删除报价任务成功！";
		}
	}

	@Override
	public String updateQuoteTask(QuoteTask quoteTask) {
		int index = quoteTaskMapper.updateByPrimaryKeySelective(quoteTask);
		sqlSession.commit();
		sqlSession.close();
		if(index<1){
			return "更新报价任务失败，请稍后重试！";
		}else{
			return "更新报价任务成功！";
		}
	}

	@Override
	public List<QuoteTask> queryQuoteTask(String taskName) {
		return quoteTaskMapper.selectByName(taskName);
	}

}
