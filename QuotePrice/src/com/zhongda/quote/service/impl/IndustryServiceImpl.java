package com.zhongda.quote.service.impl;

import java.util.List;

import com.zhongda.quote.dao.IndustryMapper;
import com.zhongda.quote.model.Industry;
import com.zhongda.quote.service.IndustryService;
import com.zhongda.quote.utils.MyBatisUtil;

public class IndustryServiceImpl implements IndustryService {

	private IndustryMapper industryMapper = MyBatisUtil.getSqlSession().getMapper(IndustryMapper.class);

	@Override
	public List<Industry> queryAllIndustry() {
		return industryMapper.selectAllIndustry();
	}

}
