package com.zhongda.quote.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.zhongda.quote.dao.SysInspectionContentMapper;
import com.zhongda.quote.model.SysInspectionContent;
import com.zhongda.quote.service.SysInspectionContentService;
import com.zhongda.quote.utils.MyBatisUtil;

public class SysInspectionContenServiceImpl implements
		SysInspectionContentService {

	private static Logger logger = Logger
			.getLogger(SysInspectionContenServiceImpl.class);

	private SqlSession sqlSession = MyBatisUtil.getSqlSession();

	private SysInspectionContentMapper sysInsContentMapper = sqlSession
			.getMapper(SysInspectionContentMapper.class);

	@Override
	public List<SysInspectionContent> querySysInspectionContentByContentName(
			String contentName) {
		List<SysInspectionContent> contentList = null;
		try {
			contentList = sysInsContentMapper.selectByName(contentName);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return contentList;
	}

}
