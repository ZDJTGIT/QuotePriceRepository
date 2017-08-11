package com.zhongda.quote.service;

import java.util.List;

import com.zhongda.quote.model.Industry;

public interface IndustryService {

	/**
	 * 查询出所有的行业
	 * @return
	 */
	List<Industry> queryAllIndustry();
}
