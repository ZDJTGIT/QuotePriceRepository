package com.zhongda.quote.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.zhongda.quote.dao.SampleBasisMapper;
import com.zhongda.quote.model.QuoteBasis;
import com.zhongda.quote.model.SampleBasis;
import com.zhongda.quote.service.SampleBasisService;
import com.zhongda.quote.utils.MyBatisUtil;

public class SampleBasisServiceImpl implements SampleBasisService{

	private static Logger logger = Logger
			.getLogger(SampleBasisServiceImpl.class);

	private SqlSession sqlSession = MyBatisUtil.getSqlSession();

	private SampleBasisMapper sampleBasisMapper = sqlSession
			.getMapper(SampleBasisMapper.class);
	
	@Override
	public SampleBasis SelectSampleBasisByInspectionContentID(
			Integer InspectionContentID) {
		SampleBasis sampleBasis = null;
		try {
			sampleBasis = sampleBasisMapper
					.SelectSampleBasisByInspectionContentID(InspectionContentID);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return sampleBasis;
	}

}
