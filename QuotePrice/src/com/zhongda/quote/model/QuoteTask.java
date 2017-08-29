package com.zhongda.quote.model;

import java.util.List;

public class QuoteTask {
	private Integer id;

	private String taskNumber;

	private String taskName;

	private String taskDescription;

	private String createUser;

	private String createDate;

	private String lastUpdateDate;

	private Double taskAmount;

	private List<QuoteProject> projectList;

	public QuoteTask() {
	}

	public QuoteTask(String taskName, String taskDescription,
			String createUser, String createDate, String lastUpdateDate) {
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.createUser = createUser;
		this.createDate = createDate;
		this.lastUpdateDate = lastUpdateDate;
	}

	public QuoteTask(Integer taskId, double taskAmount) {
		this.id = taskId;
		this.taskAmount = taskAmount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber == null ? null : taskNumber.trim();
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName == null ? null : taskName.trim();
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription == null ? null : taskDescription
				.trim();
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate == null ? null : createDate.trim();
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate == null ? null : lastUpdateDate
				.trim();
	}

	public Double getTaskAmount() {
		return taskAmount;
	}

	public void setTaskAmount(Double taskAmount) {
		this.taskAmount = taskAmount;
	}

	public List<QuoteProject> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<QuoteProject> projectList) {
		this.projectList = projectList;
	}

}