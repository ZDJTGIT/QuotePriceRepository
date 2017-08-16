package com.zhongda.quote.service;

import java.util.List;

import com.zhongda.quote.model.QuoteProject;

public interface QuoteProjectService {

	/**
	 * 查询项目根据任务id
	 * 
	 * @return
	 */
	List<QuoteProject> queryAllQuoteProjectsByTaskNmber(int taskId);
}
