package com.zhongda.quote.dao;

import java.util.List;

import com.zhongda.quote.model.QuoteProject;

public interface QuoteProjectMapper {

	/**
	 * 删除一个报价项目根据项目id
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);

	int insert(QuoteProject record);

	int insertSelective(QuoteProject record);

	QuoteProject selectByPrimaryKey(Integer id);

	/**
	 * 修改报价项目
	 * @param record
	 * @return
	 */
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

	/**
	 * 查询报价任务通过任务id和项目名
	 * @param taskId
	 * @param projectName
	 * @return
	 */
	List<QuoteProject> selectProjectByPidAndName(Integer taskId,
			String projectName);

}