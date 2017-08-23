package com.zhongda.quote.model;

public class SysInspectionContent {
	private Integer id;

	private String inspectionContentName;//1

	private Integer industryId;

	private Integer addressId;

	private Integer sampleQuantity;//4

	private String sampleQuantityRange;//3

	private Integer sampleBasisId;

	private Integer singleObjectQuantity;//7

	private String singleQuantityRange;//6

	private String chargeUnit;//8

	private Integer chargeStandard;//9

	private String chargeStandardUnit;

	private Integer quoteBasisId;//5

	private QuoteBasis quoteBasis;

	private SampleBasis sampleBasis;

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

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
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
}