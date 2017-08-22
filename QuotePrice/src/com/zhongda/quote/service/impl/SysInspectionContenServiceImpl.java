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

	//从系统中获得所有未添加的检验内容
	@Override
	public List<SysInspectionContent> selectSysInspectionContent(Integer batchID) {
		List<SysInspectionContent> sysInspectionContent = null;
		try {
			sysInspectionContent = sysInsContentMapper.selectAllSysInspectionContent(batchID);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return sysInspectionContent;
	}

	@Override
	public List<SysInspectionContent> selectAllBlurrySysInspectionContent(
			String blurryString) {
		List<SysInspectionContent> sysInspectionContent = null;
		try {
			sysInspectionContent = sysInsContentMapper.selectAllBlurrySysInspectionContent(blurryString);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			MyBatisUtil.closeSqlSession();
		}
		return sysInspectionContent;
	}
	

}
