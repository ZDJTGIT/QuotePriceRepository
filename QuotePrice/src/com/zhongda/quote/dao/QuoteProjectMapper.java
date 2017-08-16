package com.zhongda.quote.dao;

import java.util.List;

import com.zhongda.quote.model.QuoteProject;

public interface QuoteProjectMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(QuoteProject record);

	int insertSelective(QuoteProject record);

	QuoteProject selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(QuoteProject record);

	int updateByPrimaryKey(QuoteProject record);

	/**
	 * 查询项目根据任务ID
	 * 
	 * @return
	 */
	List<QuoteProject> selectByTaskNumber(int taskId);

	/**
	 * 查询id最大一行项目数据
	 * 
	 * @return
	 */
	QuoteProject selectMaxProjectId();

}