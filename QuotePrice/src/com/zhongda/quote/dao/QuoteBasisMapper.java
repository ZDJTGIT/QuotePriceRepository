package com.zhongda.quote.dao;

import com.zhongda.quote.model.QuoteBasis;

public interface QuoteBasisMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuoteBasis record);

    int insertSelective(QuoteBasis record);

    QuoteBasis selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuoteBasis record);

    int updateByPrimaryKey(QuoteBasis record);
    
    /**
	 * 传当前选中检验内容ID查报价依据
	 * @param InspectionContentID
	 * @return 报价依据
	 */
	QuoteBasis SelectQuoteBasisByInspectionContentID(
			Integer InspectionContentID);
}