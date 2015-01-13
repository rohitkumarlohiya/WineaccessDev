package com.wineaccess.sampler;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.wine.ViewWineLogisticVO;

@XmlRootElement(name = "viewSamplerLogisticsDetail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class ViewSamplerLogisticsDetailVO implements Serializable {

	private static final long serialVersionUID = -2901944990960601159L;

	List<ViewWineLogisticVO> wineLogistics;

	Set<Long> notExistIds;

	Set<Long> wineLogisticsNotFoundIds;

	public Set<Long> getWineLogisticsNotFoundIds() {
		return wineLogisticsNotFoundIds;
	}

	public void setWineLogisticsNotFoundIds(Set<Long> wineLogisticsNotFoundIds) {
		this.wineLogisticsNotFoundIds = wineLogisticsNotFoundIds;
	}

	public Set<Long> getNotExistIds() {
		return notExistIds;
	}

	public void setNotExistIds(Set<Long> notExistIds) {
		this.notExistIds = notExistIds;
	}

	public List<ViewWineLogisticVO> getWineLogistics() {
		return wineLogistics;
	}

	public void setWineLogistics(List<ViewWineLogisticVO> wineLogistics) {
		this.wineLogistics = wineLogistics;
	}

	public ViewSamplerLogisticsDetailVO() {
		super();
	}

}
