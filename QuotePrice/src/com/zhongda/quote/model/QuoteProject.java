package com.zhongda.quote.model;

public class QuoteProject {
	private Integer id;

	private String projectName;

	private Integer industryId;

	private Integer addressId;

	private Integer addressPid;

	private Integer taskId;

	private Double projectAmount;

	private Double otherAmout;

	private Industry industry;

	private Address address;

	public QuoteProject() {

	}

	public QuoteProject(String projectName, Integer taskId, Integer industryId,
			Integer addressPid, Integer addressId) {
		this.projectName = projectName;
		this.taskId = taskId;
		this.industryId = industryId;
		this.addressPid = addressPid;
		this.addressId = addressId;

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

	public Double getOtherAmout() {
		return otherAmout;
	}

	public void setOtherAmout(Double otherAmout) {
		this.otherAmout = otherAmout;
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
}