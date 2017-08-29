package com.zhongda.quote.model;

import java.util.List;

public class InspectionBatch {
	private Integer id;

	private String inspectionBatchName;

	private Integer projectId;

	private Double inspectionBatchAmount;

	private List<InspectionContent> contentList;

	public InspectionBatch() {
	}

	public InspectionBatch(Integer batchId, double batchAmount) {
		this.id = batchId;
		this.inspectionBatchAmount = batchAmount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInspectionBatchName() {
		return inspectionBatchName;
	}

	public void setInspectionBatchName(String inspectionBatchName) {
		this.inspectionBatchName = inspectionBatchName == null ? null
				: inspectionBatchName.trim();
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Double getInspectionBatchAmount() {
		return inspectionBatchAmount;
	}

	public void setInspectionBatchAmount(Double inspectionBatchAmount) {
		this.inspectionBatchAmount = inspectionBatchAmount;
	}

	public List<InspectionContent> getContentList() {
		return contentList;
	}

	public void setContentList(List<InspectionContent> contentList) {
		this.contentList = contentList;
	}

}