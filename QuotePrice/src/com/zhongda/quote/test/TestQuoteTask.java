package com.zhongda.quote.test;

import com.zhongda.quote.model.QuoteTask;
import com.zhongda.quote.service.impl.QuoteTaskServiceImpl;

public class TestQuoteTask {

	public static void main(String[] args) {

		QuoteTask quoteTask = new QuoteTask();
		quoteTask.setTaskNumber("ZD201708080003");
		quoteTask.setTaskName("Test任务");
		quoteTask.setTaskDescription("呵呵");
		quoteTask.setIndustry(1);
		quoteTask.setCreateUser("haixin");
		quoteTask.setTaskAmount(0.0);
		//quoteTask.setCreateDate(new Date().toString());
		//quoteTask.setLastUpdateDate(new Date().toString());
		new QuoteTaskServiceImpl().createQuoteTask(quoteTask);
	}

}
