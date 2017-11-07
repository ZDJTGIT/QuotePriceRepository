package com.zhongda.quote.model;

public class QuoteBasis {
	private Integer id;

	private String inspectionContentName;

	private Integer industryId;

	private Integer addressId;

	private String basisFileNumber;

	private String basisFileName;

	private String basisFileIndex;

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

	public String getBasisFileNumber() {
		return basisFileNumber;
	}

	public void setBasisFileNumber(String basisFileNumber) {
		this.basisFileNumber = basisFileNumber == null ? null : basisFileNumber
				.trim();
	}

	public String getBasisFileName() {
		return basisFileName;
	}

	public void setBasisFileName(String basisFileName) {
		this.basisFileName = basisFileName == null ? null : basisFileName
				.trim();
	}

	public String getBasisFileIndex() {
		return basisFileIndex;
	}

	public void setBasisFileIndex(String basisFileIndex) {
		this.basisFileIndex = basisFileIndex == null ? null : basisFileIndex
				.trim();
	}

	@Override
	public String toString() {
		return "QuoteBasis [id=" + id + ", inspectionContentName="
				+ inspectionContentName + ", industryId=" + industryId
				+ ", addressId=" + addressId + ", basisFileNumber="
				+ basisFileNumber + ", basisFileName=" + basisFileName
				+ ", basisFileIndex=" + basisFileIndex + "]";
	}

}