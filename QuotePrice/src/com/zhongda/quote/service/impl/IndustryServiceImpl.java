package com.zhongda.quote.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.zhongda.quote.dao.IndustryMapper;
import com.zhongda.quote.model.Industry;
import com.zhongda.quote.service.IndustryService;
import com.zhongda.quote.utils.MyBatisUtil;

public class IndustryServiceImpl implements IndustryService {

	private static Logger logger = Logger.getLogger(IndustryServiceImpl.class);

	private SqlSession sqlSession = MyBatisUtil.getSqlSession();
	private IndustryMapper industryMapper = MyBatisUtil.getSqlSession().getMapper(IndustryMapper.class);

	@Override
	public List<Industry> queryAllIndustry() {
		List<Industry> industryList = null;
		try{
			industryList = industryMapper.selectAllIndustry();
		}catch(Exception e){
			logger.info(e.getMessage());
		}finally{
			sqlSession.close();
		}
		return industryList;

	}

}
