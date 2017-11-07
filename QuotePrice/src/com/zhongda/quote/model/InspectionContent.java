package com.zhongda.quote.model;

public class InspectionContent {
	private Integer id;

	private Integer sourceId;

	private String inspectionContentName;

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

	private QuoteBasis quoteBasis;

	private SampleBasis sampleBasis;

	public InspectionContent() {

	}

	public InspectionContent(Integer sourceId, String inspectionContentName,
			Integer sampleQuantity, String sampleQuantityRange,
			Integer sampleBasisId, Integer singleObjectQuantity,
			String singleQuantityRange, String chargeUnit,
			Integer chargeStandard, String chargeStandardUnit,
			Integer quoteBasisId, Double inspectionContentAmount) {
		this.sourceId = sourceId;
		this.inspectionContentName = inspectionContentName;
		this.sampleQuantity = sampleQuantity;
		this.sampleQuantityRange = sampleQuantityRange;
		this.sampleBasisId = sampleBasisId;
		this.singleObjectQuantity = singleObjectQuantity;
		this.singleQuantityRange = singleQuantityRange;
		this.chargeUnit = chargeUnit;
		this.chargeStandard = chargeStandard;
		this.chargeStandardUnit = chargeStandardUnit;
		this.quoteBasisId = quoteBasisId;
		this.inspectionContentAmount = inspectionContentAmount;
	}

	public InspectionContent(Integer contentId, Integer samplesQuantityInt,
			Integer singleObjectQuantityInt, Double contentAmount) {
		this.id = contentId;
		this.sampleQuantity = samplesQuantityInt;
		this.singleObjectQuantity = singleObjectQuantityInt;
		this.inspectionContentAmount = contentAmount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public String getInspectionContentName() {
		return inspectionContentName;
	}

	public void setInspectionContentName(String inspectionContentName) {
		this.inspectionContentName = inspectionContentName == null ? null
				: inspectionContentName.trim();
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

	public QuoteBasis getQuoteBasis() {
		return quoteBasis;
	}

	public void setQuoteBasis(QuoteBasis quoteBasis) {
		this.quoteBasis = quoteBasis;
	}

	public SampleBasis getSampleBasis() {
		return sampleBasis;
	}

	public void setSampleBasis(SampleBasis sampleBasis) {
		this.sampleBasis = sampleBasis;
	}

	@Override
	public String toString() {
		return "InspectionContent [id=" + id + ", sourceId=" + sourceId
				+ ", inspectionContentName=" + inspectionContentName
				+ ", sampleQuantity=" + sampleQuantity
				+ ", sampleQuantityRange=" + sampleQuantityRange
				+ ", sampleBasisId=" + sampleBasisId
				+ ", singleObjectQuantity=" + singleObjectQuantity
				+ ", singleQuantityRange=" + singleQuantityRange
				+ ", chargeUnit=" + chargeUnit + ", chargeStandard="
				+ chargeStandard + ", chargeStandardUnit=" + chargeStandardUnit
				+ ", quoteBasisId=" + quoteBasisId + ", batchId=" + batchId
				+ ", inspectionContentAmount=" + inspectionContentAmount
				+ ", quoteBasis=" + quoteBasis + ", sampleBasis=" + sampleBasis
				+ "]";
	}

}