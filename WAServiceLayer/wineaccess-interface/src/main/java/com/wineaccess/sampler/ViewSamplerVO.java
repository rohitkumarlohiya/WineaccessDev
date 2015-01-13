package com.wineaccess.sampler;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1 
 * VO of view Sampler
 */
@XmlRootElement
@XmlType(name = "viewSampler")
public class ViewSamplerVO implements Serializable {

	private static final long serialVersionUID = 1329828985438651813L;

	private String samplerName;
	private long samplerId;
	private SamplerKeyMetrics keyMetrics;

	public String getSamplerName() {
		return samplerName;
	}

	public void setSamplerName(String samplerName) {
		this.samplerName = samplerName;
	}

	public long getSamplerId() {
		return samplerId;
	}

	public void setSamplerId(long samplerId) {
		this.samplerId = samplerId;
	}

	public SamplerKeyMetrics getKeyMetrics() {
		return keyMetrics;
	}

	public void setKeyMetrics(SamplerKeyMetrics keyMetrics) {
		this.keyMetrics = keyMetrics;
	}

}
