package com.wineaccess.security.masterdata;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class MasterDataMultipleDeleteVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8488344595777009478L;

	@XmlElement(name="nonExistingList")
	private MultipleDeleteModelForNotExists notExists;	
	@XmlElement(name="failureList")
	private MultipleDeleteModelForDependency dependency;	
	@XmlElement(name="successList")
	private MultipleDeleteModelForDependency canBeDeleted;

	public MultipleDeleteModelForNotExists getNotExists() {
		return notExists;
	}

	public void setNotExists(MultipleDeleteModelForNotExists notExists) {
		this.notExists = notExists;
	}

	public MultipleDeleteModelForDependency getDependency() {
		return dependency;
	}

	public void setDependency(MultipleDeleteModelForDependency dependency) {
		this.dependency = dependency;
	}

	public MultipleDeleteModelForDependency getCanBeDeleted() {
		return canBeDeleted;
	}

	public void setCanBeDeleted(MultipleDeleteModelForDependency canBeDeleted) {
		this.canBeDeleted = canBeDeleted;
	}

	public MasterDataMultipleDeleteVO() {
		super();
		notExists = new MultipleDeleteModelForNotExists();
		dependency = new MultipleDeleteModelForDependency();
		canBeDeleted = new MultipleDeleteModelForDependency();
	}

	public MasterDataMultipleDeleteVO(
			MultipleDeleteModelForNotExists notExists,
			MultipleDeleteModelForDependency dependency,
			MultipleDeleteModelForDependency canBeDeleted) {
		super();
		this.notExists = notExists;
		this.dependency = dependency;
		this.canBeDeleted = canBeDeleted;
	}

}
