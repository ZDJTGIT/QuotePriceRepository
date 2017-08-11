package com.zhongda.quote.service;

import java.util.List;

import com.zhongda.quote.model.QuoteTask;

public interface QuoteTaskService {

	/**
	 * 创建一个报价任务
	 * @param quoteTask
	 * @return 返回当前插入报价任务，可通过返回的任务是否为null来判断创建是否成功
	 */
	QuoteTask createQuoteTask(QuoteTask quoteTask);

	/**
	 * 删除一个报价任务根据任务id
	 * @param id
	 * @return 返回成功或失败的提示信息
	 */
	boolean deleteQuoteTask(Integer id);

	/**
	 * 修改一个报价任务
	 * @param quoteTask
	 * @return 返回成功或失败的提示信息
	 */
	boolean updateQuoteTask(QuoteTask quoteTask);

	/**
	 * 查询报价任务根据任务名称
	 * @param taskName
	 * @return
	 */
	List<QuoteTask> queryQuoteTaskByName(String taskName);

	/**
	 * 查询所有报价任务
	 * @return
	 */
	List<QuoteTask> queryAllQuoteTask();

	/**
	 *  查询报价任务根据任务编号
	 * @param taskNumber
	 * @return
	 */
	QuoteTask queryQuoteTaskByNumber(String taskNumber);
}
