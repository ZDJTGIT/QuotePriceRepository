package com.zhongda.quote.model;

public class SampleBasis {
    private Integer id;

    private String inspectionContentName;

    private String inspectionMethodName;

    private String basisFileNumber;

    private String basisFileName;

    private String basisFileIndex;

    private String basisImage;

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

    public String getBasisFileNumber() {
        return basisFileNumber;
    }

    public void setBasisFileNumber(String basisFileNumber) {
        this.basisFileNumber = basisFileNumber == null ? null : basisFileNumber.trim();
    }

    public String getBasisFileName() {
        return basisFileName;
    }

    public void setBasisFileName(String basisFileName) {
        this.basisFileName = basisFileName == null ? null : basisFileName.trim();
    }

    public String getBasisFileIndex() {
        return basisFileIndex;
    }

    public void setBasisFileIndex(String basisFileIndex) {
        this.basisFileIndex = basisFileIndex == null ? null : basisFileIndex.trim();
    }

    public String getBasisImage() {
        return basisImage;
    }

    public void setBasisImage(String basisImage) {
        this.basisImage = basisImage == null ? null : basisImage.trim();
    }
}