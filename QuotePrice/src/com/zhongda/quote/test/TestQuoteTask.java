package com.zhongda.quote.test;

import java.util.Date;
import java.util.List;

import com.zhongda.quote.model.QuoteTask;
import com.zhongda.quote.service.impl.QuoteTaskServiceImpl;

public class TestQuoteTask {

	public static void main(String[] args) {

		//TestInsert();

		System.out.println("查询数据库开始");
		long start = new Date().getTime();
		List<QuoteTask> taskList = new QuoteTaskServiceImpl().queryAllQuoteTask();
		long end = new Date().getTime();
		System.out.println("查询数据库结束");
		System.out.println("此次查询耗时："+(end-start)/1000+"秒");
	}


	private static void TestInsert() {
		QuoteTask quoteTask = new QuoteTask();
		quoteTask.setTaskNumber("ZD201708080003");
		quoteTask.setTaskName("Test任务");
		quoteTask.setTaskDescription("呵呵");
		quoteTask.setIndustry(1);
		quoteTask.setCreateUser("haixin");
		quoteTask.setTaskAmount(0.0);
		quoteTask.setCreateDate(new Date().toString());
		quoteTask.setLastUpdateDate(new Date().toString());
		new QuoteTaskServiceImpl().createQuoteTask(quoteTask);
	}

}
