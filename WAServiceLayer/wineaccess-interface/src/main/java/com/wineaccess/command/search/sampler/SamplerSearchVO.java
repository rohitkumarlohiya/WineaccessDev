package com.wineaccess.command.search.sampler;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.springframework.beans.BeanUtils;
@XmlRootElement
@XmlType(name="samplers")
public class SamplerSearchVO extends SamplerSearchPO {

	private int totalRecordCount;

	private int count;

	private List<SamplerModelVO> samplerModel;

	public SamplerSearchVO() {
	}

	public SamplerSearchVO(List<SamplerModelVO> samplerModel, SamplerSearchPO sampleSearchPO, int totalRecordCount, int count) {
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
