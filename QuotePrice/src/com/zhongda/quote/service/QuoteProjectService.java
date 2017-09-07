package com.zhongda.quote.service;

import java.util.List;
import java.util.Map;

import com.zhongda.quote.model.QuoteProject;
import com.zhongda.quote.model.QuoteTask;

public interface QuoteProjectService {

	/**
	 * 查询项目根据任务id
	 *
	 * @return
	 */
	List<QuoteProject> queryAllQuoteProjectsByTaskNmber(int taskId);

	/**
	 * 插入数据到项目表返回刚插入的数据
	 *
	 * @param quoteProject
	 *
	 * @return
	 */
	QuoteProject createProject(QuoteProject quoteProject);

	/**
	 * 删除一个报价项目根据项目id,同时修改任务的金额
	 * @param id
	 * @return 返回成功或失败的提示信息
	 */
	boolean deleteQuoteProject(Integer id, double taskAmount);

	/**
	 * 联动插入一条项目同时插入对应的检验批已经检验内容
	 * @param quoteProject 需插入的项目对象
	 * @param batchMap 需插入的检验批已经检验内容
	 * @param taskAmount 需修改的任务金额
	 * @return 插入成功的所有数据
	 */
	Map<String, Object> createProjectAndBatchAndContent(QuoteProject quoteProject,
			Map<String, Map<String, Object>> batchMap, double taskAmount);

	/**
	 * 修改项目,同时修改任务金额
	 * @param quoteProject 新的项目
	 * @return 返回修改后的项目
	 */
	QuoteProject updateProject(QuoteProject quoteProject, QuoteTask quoteTask);

	/**
	 * 修改报价项目通过项目名和任务id
	 * @param taskId
	 * @param projectName
	 * @return
	 */
	List<QuoteProject> queryProjectByPidAndName(Integer taskId, String projectName);
}
