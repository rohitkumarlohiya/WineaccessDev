package com.wineaccess.sampler;

import com.wineaccess.common.BulkDeleteVO;

public class DeleteSamplerProductVO extends BulkDeleteVO<SamplerProductDetails> {

	private Long samplerId;

	public Long getSamplerId() {
		return samplerId;
	}

	public void setSamplerId(Long samplerId) {
		this.samplerId = samplerId;
	}
	
}