package com.wineaccess.winery;


import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.application.validation.annotation.ValidatingListAnnotation;
import com.wineaccess.command.BasePO;

@XmlRootElement(name="winery")
public class WineryDeletePO extends BasePO implements Serializable{
	private static final long serialVersionUID = -2879057461758940654L;
	
	@Valid
	@NotNull(message = "WINERY_ERROR_125")
	@ValidatingListAnnotation(message="WINERY_ERROR_125")
	private List<Long> wineryIds;
	
	
	private Boolean isForceDelete;	
		
	public List<Long> getWineryIds() {
		return wineryIds;
	}
	public void setWineryIds(List<Long> wineryIds) {
		this.wineryIds = wineryIds;
	}
	public Boolean getIsForceDelete() {
		return isForceDelete;
	}
	public void setIsForceDelete(Boolean isForceDelete) {
		this.isForceDelete = isForceDelete;
	}

}