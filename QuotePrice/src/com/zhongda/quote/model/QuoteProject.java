package com.zhongda.quote.model;

import java.util.List;

public class QuoteProject {
	private Integer id;

	private String projectName;

	private Integer industryId;

	private Integer addressId;

	private Integer addressPid;

	private Integer taskId;

	private Double projectAmount;

	private Double otherAmount;

	private Industry industry;

	private Address address;

	private List<InspectionBatch> batchList;

	public QuoteProject() {

	}

	public QuoteProject(String projectName, int industryId, int addressId,
			int addressPid, int taskId, double projectAmount, double otherAmount) {
		this.projectName = projectName;
		this.industryId = industryId;
		this.addressId = addressId;
		this.addressPid = addressPid;
		this.taskId = taskId;
		this.projectAmount = projectAmount;
		this.otherAmount = otherAmount;
	}

	public QuoteProject(Integer projectId, double projectAmount) {
		this.id = projectId;
		this.projectAmount = projectAmount;
	}

	public QuoteProject(Integer projectId, String projectName, double otherAmount) {
		this.id = projectId;
		this.projectName = projectName;
		this.otherAmount = otherAmount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName == null ? null : projectName.trim();
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

	public Integer getAddressPid() {
		return addressPid;
	}

	public void setAddressPid(Integer addressPid) {
		this.addressPid = addressPid;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Double getProjectAmount() {
		return projectAmount;
	}

	public void setProjectAmount(Double projectAmount) {
		this.projectAmount = projectAmount;
	}

	public Double getOtherAmount() {
		return otherAmount;
	}

	public void setOtherAmount(Double otherAmount) {
		this.otherAmount = otherAmount;
	}

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<InspectionBatch> getBatchList() {
		return batchList;
	}

	public void setBatchList(List<InspectionBatch> batchList) {
		this.batchList = batchList;
	}
}