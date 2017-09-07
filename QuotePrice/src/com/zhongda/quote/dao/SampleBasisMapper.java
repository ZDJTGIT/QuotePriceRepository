package com.zhongda.quote.dao;

import com.zhongda.quote.model.SampleBasis;

public interface SampleBasisMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SampleBasis record);

    int insertSelective(SampleBasis record);

    SampleBasis selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SampleBasis record);

    int updateByPrimaryKey(SampleBasis record);
    
    /**
	 * 传当前选中检验内容ID查抽样依据
	 * @param InspectionContentID
	 * @return 报价依据
	 */
    SampleBasis SelectSampleBasisByInspectionContentID(
			Integer InspectionContentID);
}