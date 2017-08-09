package com.zhongda.quote.model;

public class InspectionContent {
    private Integer id;

	private String inspectionContentName;

	private String inspectionMethodName;

	private Integer sampleQuantity;

	private String sampleQuantityRange;

	private Integer sampleBasisId;

	private Integer singleObjectQuantity;

	private String singleQuantityRange;

	private String chargeUnit;

	private Integer chargeStandard;

	private String chargeStandardUnit;

	private Integer quoteBasisId;

	private Integer batchId;

	private Double inspectionContentAmount;

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
		this.inspectionContentName = inspectionContentName == null ? null
				: inspectionContentName.trim();
	}

	public String getInspectionMethodName() {
		return inspectionMethodName;
	}

	public void setInspectionMethodName(String inspectionMethodName) {
		this.inspectionMethodName = inspectionMethodName == null ? null
				: inspectionMethodName.trim();
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
		this.sampleQuantityRange = sampleQuantityRange == null ? null
				: sampleQuantityRange.trim();
	}

	public Integer getSampleBasisId() {
		return sampleBasisId;
	}

	public void setSampleBasisId(Integer sampleBasisId) {
		this.sampleBasisId = sampleBasisId;
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
		this.singleQuantityRange = singleQuantityRange == null ? null
				: singleQuantityRange.trim();
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
		this.chargeStandardUnit = chargeStandardUnit == null ? null
				: chargeStandardUnit.trim();
	}

	public Integer getQuoteBasisId() {
		return quoteBasisId;
	}

	public void setQuoteBasisId(Integer quoteBasisId) {
		this.quoteBasisId = quoteBasisId;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public Double getInspectionContentAmount() {
		return inspectionContentAmount;
	}

	public void setInspectionContentAmount(Double inspectionContentAmount) {
		this.inspectionContentAmount = inspectionContentAmount;
	}

}