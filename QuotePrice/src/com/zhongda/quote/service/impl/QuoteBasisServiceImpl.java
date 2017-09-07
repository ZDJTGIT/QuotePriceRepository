package com.zhongda.quote.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.zhongda.quote.dao.QuoteBasisMapper;
import com.zhongda.quote.model.QuoteBasis;
import com.zhongda.quote.service.QuoteBasisService;
import com.zhongda.quote.utils.MyBatisUtil;

public class QuoteBasisServiceImpl implements QuoteBasisService{

	private static Logger logger = Logger
			.getLogger(QuoteBasisServiceImpl.class);

	private SqlSession sqlSession = MyBatisUtil.getSqlSession();

	private QuoteBasisMapper quoteBasisMapper = sqlSession
			.getMapper(QuoteBasisMapper.class);
	
	@Override
	public QuoteBasis SelectQuoteBasisByInspectionContentID(
			Integer InspectionContentID) {
		QuoteBasis quoteBasis = null;
		try {
			quoteBasis = quoteBasisMapper
					.SelectQuoteBasisByInspectionContentID(InspectionContentID);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return quoteBasis;
	}

}
