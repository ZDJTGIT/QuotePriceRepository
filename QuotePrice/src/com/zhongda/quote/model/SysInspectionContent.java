package com.zhongda.quote.model;

public class SysInspectionContent {
    private Integer id;

    private String inspectionContentName;

    private String inspectionMethodName;

    private Integer inspectionContentFlag;

    private Integer industryFlag;

    private Integer addressFlag;

    private Integer sampleQuantity;

    private String sampleQuantityRange;

    private Integer sampleBasicId;

    private Integer singleObjectQuantity;

    private String singleQuantityRange;

    private String chargeUnit;

    private Integer chargeStandard;

    private String chargeStandardUnit;

    private Integer quoteBasisId;

    private QuoteBasis quoteBasis;

    private SampleBasis sampleBbasis;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInspectionContentName() {
        return inspectionContentName;
    }

    public void setInspectionContentName(String inspectionContentName) {
        this.inspectionContentName = inspectionContentName == null ? null : inspectionContentName.trim();
    }

    public String getInspectionMethodName() {
        return inspectionMethodName;
    }

    public void setInspectionMethodName(String inspectionMethodName) {
        this.inspectionMethodName = inspectionMethodName == null ? null : inspectionMethodName.trim();
    }

    public Integer getInspectionContentFlag() {
        return inspectionContentFlag;
    }

    public void setInspectionContentFlag(Integer inspectionContentFlag) {
        this.inspectionContentFlag = inspectionContentFlag;
    }

    public Integer getIndustryFlag() {
        return industryFlag;
    }

    public void setIndustryFlag(Integer industryFlag) {
        this.industryFlag = industryFlag;
    }

    public Integer getAddressFlag() {
        return addressFlag;
    }

    public void setAddressFlag(Integer addressFlag) {
        this.addressFlag = addressFlag;
    }

    public Integer getSampleQuantity() {
        return sampleQuantity;
    }

    public void setSampleQuantity(Integer sampleQuantity) {
        this.sampleQuantity = sampleQuantity;
    }

    public String getSampleQuantityRange() {
        return sampleQuantityRange;
    }

    public void setSampleQuantityRange(String sampleQuantityRange) {
        this.sampleQuantityRange = sampleQuantityRange == null ? null : sampleQuantityRange.trim();
    }

    public Integer getSampleBasicId() {
        return sampleBasicId;
    }

    public void setSampleBasicId(Integer sampleBasicId) {
        this.sampleBasicId = sampleBasicId;
    }

    public Integer getSingleObjectQuantity() {
        return singleObjectQuantity;
    }

    public void setSingleObjectQuantity(Integer singleObjectQuantity) {
        this.singleObjectQuantity = singleObjectQuantity;
    }

    public String getSingleQuantityRange() {
        return singleQuantityRange;
    }

    public void setSingleQuantityRange(String singleQuantityRange) {
        this.singleQuantityRange = singleQuantityRange == null ? null : singleQuantityRange.trim();
    }

    public String getChargeUnit() {
        return chargeUnit;
    }

    public void setChargeUnit(String chargeUnit) {
        this.chargeUnit = chargeUnit == null ? null : chargeUnit.trim();
    }

    public Integer getChargeStandard() {
        return chargeStandard;
    }

    public void setChargeStandard(Integer chargeStandard) {
        this.chargeStandard = chargeStandard;
    }

    public String getChargeStandardUnit() {
        return chargeStandardUnit;
    }

    public void setChargeStandardUnit(String chargeStandardUnit) {
        this.chargeStandardUnit = chargeStandardUnit == null ? null : chargeStandardUnit.trim();
    }

    public Integer getQuoteBasisId() {
        return quoteBasisId;
    }

    public void setQuoteBasisId(Integer quoteBasisId) {
        this.quoteBasisId = quoteBasisId;
    }

	public QuoteBasis getQuoteBasis() {
		return quoteBasis;
	}

	public void setQuoteBasis(QuoteBasis quoteBasis) {
		this.quoteBasis = quoteBasis;
	}

	public SampleBasis getSampleBbasis() {
		return sampleBbasis;
	}

	public void setSampleBbasis(SampleBasis sampleBbasis) {
		this.sampleBbasis = sampleBbasis;
	}
}