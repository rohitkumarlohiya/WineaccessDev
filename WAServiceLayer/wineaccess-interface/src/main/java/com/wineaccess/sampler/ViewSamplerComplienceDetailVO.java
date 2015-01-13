package com.wineaccess.sampler;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "viewSamplerComplienceDetail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class ViewSamplerComplienceDetailVO implements Serializable {

	private static final long serialVersionUID = -2901944990960601159L;

	List<ViewWineLicenseAndPermitVO> wineLicenseAndPermits;

	Set<Long> notExistIds;

	Set<Long> wineLicenseAndPermitNotFoundIds;

	public List<ViewWineLicenseAndPermitVO> getWineLicenseAndPermits() {
		return wineLicenseAndPermits;
	}

	public void setWineLicenseAndPermits(
			List<ViewWineLicenseAndPermitVO> wineLicenseAndPermitVOs) {
		this.wineLicenseAndPermits = wineLicenseAndPermitVOs;
	}

	public Set<Long> getNotExistIds() {
		return notExistIds;
	}

	public void setNotExistIds(Set<Long> notExistIds) {
		this.notExistIds = notExistIds;
	}

	public Set<Long> getWineLicenseAndPermitNotFoundIds() {
		return wineLicenseAndPermitNotFoundIds;
	}

	public void setWineLicenseAndPermitNotFoundIds(
			Set<Long> wineLicenseAndPermitNotFoundIds) {
		this.wineLicenseAndPermitNotFoundIds = wineLicenseAndPermitNotFoundIds;
	}

	public ViewSamplerComplienceDetailVO() {
		super();
	}

}
