package com.wineaccess.command.search.sampler;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.BeanUtils;

import com.google.gson.annotations.SerializedName;

public class SamplerAdvSearchVO extends SamplerAdvSearchPO {

	private int totalRecordCount;

	private int count;

	@XmlElement(name="sampler")
	@SerializedName(value="sampler")
	private List<SamplerModelVO> samplerModel;

	public SamplerAdvSearchVO() {
	}

	public SamplerAdvSearchVO(List<SamplerModelVO> samplerModel,
			SamplerAdvSearchPO sampleSearchPO, int totalRecordCount, int count) {
		this.samplerModel = samplerModel;
		this.totalRecordCount = totalRecordCount;
		this.count = count;
		BeanUtils.copyProperties(sampleSearchPO, this);
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<SamplerModelVO> getSamplerModel() {
		return samplerModel;
	}

	public void setSamplerModel(List<SamplerModelVO> samplerModel) {
		this.samplerModel = samplerModel;
	}
}
