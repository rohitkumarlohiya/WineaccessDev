package com.wineaccess.winery;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author gaurav.agarwal1
 * 
 * VO of view winery and will show the winery details and key metrics
 */
@XmlRootElement
@XmlType(name = "viewWinery")
public class ViewWineryVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private WineryDetails wineryDetails;
	private WineryKeyMetrics keyMetrics;

	public WineryDetails getWineryDetails() {
		return wineryDetails;
	}

	public void setWineryDetails(WineryDetails wineryDetails) {
		this.wineryDetails = wineryDetails;
	}

	public WineryKeyMetrics getKeyMetrics() {
		return keyMetrics;
	}

	public void setKeyMetrics(WineryKeyMetrics keyMetrics) {
		this.keyMetrics = keyMetrics;
	}

}