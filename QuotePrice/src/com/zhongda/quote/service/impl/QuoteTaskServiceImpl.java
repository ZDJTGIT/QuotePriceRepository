package com.zhongda.quote.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.zhongda.quote.dao.QuoteTaskMapper;
import com.zhongda.quote.model.QuoteTask;
import com.zhongda.quote.service.QuoteTaskService;
import com.zhongda.quote.utils.MyBatisUtil;

public class QuoteTaskServiceImpl implements QuoteTaskService {

	private SqlSession sqlSession = MyBatisUtil.getSqlSession();
	private QuoteTaskMapper quoteTaskMapper = sqlSession.getMapper(QuoteTaskMapper.class);

	@Override
	public String createQuoteTask(QuoteTask quoteTask) {
		int index = quoteTaskMapper.insert(quoteTask);
		sqlSession.commit();
		if(index<1){
			return "创建报价任务失败，请稍后重试！";
		}else{
			return "创建报价任务成功！";
		}
	}

	@Override
	public String deleteQuoteTask(Integer id) {
		int index = quoteTaskMapper.deleteByPrimaryKey(id);
		sqlSession.commit();
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
