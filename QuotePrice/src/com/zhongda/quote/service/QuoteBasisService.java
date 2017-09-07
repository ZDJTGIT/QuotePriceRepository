package com.zhongda.quote.service;

import com.zhongda.quote.model.QuoteBasis;

public interface QuoteBasisService {

	/**
	 * 传当前选中检验内容ID查报价依据
	 * @param InspectionContentID
	 * @return 报价依据
	 */
	QuoteBasis SelectQuoteBasisByInspectionContentID(
			Integer InspectionContentID);
}
