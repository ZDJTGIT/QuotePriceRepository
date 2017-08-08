package com.zhongda.quote.service;

import java.util.List;

import com.zhongda.quote.model.QuoteTask;

public interface QuoteTaskService {

	/**
	 * 创建一个报价任务
	 * @param quoteTask
	 * @return 返回成功或失败的提示信息
	 */
	String createQuoteTask(QuoteTask quoteTask);

	/**
	 * 删除一个报价任务根据任务id
	 * @param id
	 * @return 返回成功或失败的提示信息
	 */
	String deleteQuoteTask(Integer id);

	/**
	 * 修改一个报价任务
	 * @param quoteTask
	 * @return 返回成功或失败的提示信息
	 */
	String updateQuoteTask(QuoteTask quoteTask);

	/**
	 * 查询报价任务根据任务名称
	 * @param taskName
	 * @return
	 */
	List<QuoteTask> queryQuoteTask(String taskName);
}
