package com.wineaccess.sampler;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.wineaccess.winelicensedetail.WineLicenseDetailViewVO;
import com.wineaccess.winepermit.WinePermitDetailVO;
@XmlRootElement(name = "wineLicenseDetail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class ViewWineLicenseAndPermitVO implements Serializable {

	private static final long serialVersionUID = -1756438328690009842L;

	private WineLicenseDetailViewVO wineLicenseDetail;

	private WinePermitDetailVO winePermitDetail;

	public WineLicenseDetailViewVO getWineLicenseDetail() {
		return wineLicenseDetail;
	}

	public void setWineLicenseDetail(
			WineLicenseDetailViewVO wineLicenseDetailViewVO) {
		this.wineLicenseDetail = wineLicenseDetailViewVO;
	}

	public WinePermitDetailVO getWinePermitDetail() {
		return winePermitDetail;
	}

	public void setWinePermitDetail(WinePermitDetailVO winePermitDetailVO) {
		this.winePermitDetail = winePermitDetailVO;
	}

	public ViewWineLicenseAndPermitVO() {
		super();
	}

}
