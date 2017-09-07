package com.zhongda.quote.model;

public class Industry {
    private Integer id;

    private String industryName;

    public Industry() {
	}

    public Industry(Integer industryId) {
    	this.id = industryId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName == null ? null : industryName.trim();
    }
}