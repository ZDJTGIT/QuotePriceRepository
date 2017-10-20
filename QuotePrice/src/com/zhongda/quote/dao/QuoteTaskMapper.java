package com.zhongda.quote.dao;

import java.util.List;

import com.zhongda.quote.model.QuoteTask;

public interface QuoteTaskMapper {

	/**
	 * 删除一条报价任务根据任务id
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);

	int insert(QuoteTask record);

	/**
	 * 插入一条报价任务
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(QuoteTask record);

	QuoteTask selectByPrimaryKey(Integer id);

	/**
	 * 修改一条报价任务
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(QuoteTask record);

	int updateByPrimaryKey(QuoteTask record);

	/**
	 * 根据任务名模糊查询符合条件任务
	 * 
	 * @param taskName
	 * @return
	 */
	List<QuoteTask> selectByName(String taskName);

	/**
	 * 查询所有报价任务任务
	 * 
	 * @return
	 */
	List<QuoteTask> selectAll();

	/**
	 * 根据任务编号查询任务
	 * 
	 * @param taskNumber
	 * @return
	 */
	QuoteTask selectByNumber(String taskNumber);
	
	/**
	 * 查询id最大一行项目数据
	 *
	 * @return
	 */
	String selectMaxTaskNumber();

}