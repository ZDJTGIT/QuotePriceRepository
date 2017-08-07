package com.zhongda.quote.model;

public class InspectionContent {
    private Integer id;

    private String inspectionContentName;

    private Integer inspectionObjectQuantity;

    private Integer singleObjectQuantity;

    private String singleQuantityRange;

    private Integer sampleBasisId;

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
        this.inspectionContentName = inspectionContentName == null ? null : inspectionContentName.trim();
    }

    public Integer getInspectionObjectQuantity() {
        return inspectionObjectQuantity;
    }

    public void setInspectionObjectQuantity(Integer inspectionObjectQuantity) {
        this.inspectionObjectQuantity = inspectionObjectQuantity;
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

    public Integer getSampleBasisId() {
        return sampleBasisId;
    }

    public void setSampleBasisId(Integer sampleBasisId) {
        this.sampleBasisId = sampleBasisId;
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