package com.wineaccess.emailtemplate;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class EmailTemplateMultipleDeleteVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8488344595777009478L;

	
	private ETMultipleDeleteModelForNotExists notExists;	
	private ETMultipleDeleteModelForDependency dependency;	
	private ETMultipleDeleteModelForDependency canBeDeleted;

	public ETMultipleDeleteModelForNotExists getNotExists() {
		return notExists;
	}

	public void setNotExists(ETMultipleDeleteModelForNotExists notExists) {
		this.notExists = notExists;
	}

	public ETMultipleDeleteModelForDependency getDependency() {
		return dependency;
	}

	public void setDependency(ETMultipleDeleteModelForDependency dependency) {
		this.dependency = dependency;
	}

	public ETMultipleDeleteModelForDependency getCanBeDeleted() {
		return canBeDeleted;
	}

	public void setCanBeDeleted(ETMultipleDeleteModelForDependency canBeDeleted) {
		this.canBeDeleted = canBeDeleted;
	}

	public EmailTemplateMultipleDeleteVO() {
		super();
		notExists = new ETMultipleDeleteModelForNotExists();
		dependency = new ETMultipleDeleteModelForDependency();
		canBeDeleted = new ETMultipleDeleteModelForDependency();
	}

	public EmailTemplateMultipleDeleteVO(
			ETMultipleDeleteModelForNotExists notExists,
			ETMultipleDeleteModelForDependency dependency,
			ETMultipleDeleteModelForDependency canBeDeleted) {
		super();
		this.notExists = notExists;
		this.dependency = dependency;
		this.canBeDeleted = canBeDeleted;
	}

}
