package com.wineaccess.importer;


import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="deleteImporterResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType()
public class DeleteImporterVO implements Serializable {

	private static final long serialVersionUID = -7060983258610066202L;

	@XmlElementWrapper(name="successList")
	private List<ImporterVO> canBeDeleted = null;
	@XmlElementWrapper(name="failureList")
	private List<ImporterVO> canNotBeDeleted = null;
	@XmlElementWrapper(name="nonExistingList")
	private List<Long> nonExisting = null;
	public DeleteImporterVO(List<ImporterVO> canBeDeleted,
			List<ImporterVO> canNotBeDeleted, List<Long> nonExisting) {
		super();
		this.canBeDeleted = canBeDeleted;
		this.canNotBeDeleted = canNotBeDeleted;
		this.nonExisting = nonExisting;
	}
	public DeleteImporterVO() {
		super();
	}
	public List<ImporterVO> getCanBeDeleted() {
		return canBeDeleted;
	}
	public void setCanBeDeleted(List<ImporterVO> canBeDeleted) {
		this.canBeDeleted = canBeDeleted;
	}
	public List<ImporterVO> getCanNotBeDeleted() {
		return canNotBeDeleted;
	}
	public void setCanNotBeDeleted(List<ImporterVO> canNotBeDeleted) {
		this.canNotBeDeleted = canNotBeDeleted;
	}
	public List<Long> getNonExisting() {
		return nonExisting;
	}
	public void setNonExisting(List<Long> nonExisting) {
		this.nonExisting = nonExisting;
	}


}
