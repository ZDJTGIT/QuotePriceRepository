package com.zhongda.quote.service;

import com.zhongda.quote.model.SampleBasis;

public interface SampleBasisService {

	 /**
	   * 传当前选中检验内容ID查抽样依据
	   * @param InspectionContentID
	   * @return 报价依据
	   */
	    SampleBasis SelectSampleBasisByInspectionContentID(
				Integer InspectionContentID);
}
