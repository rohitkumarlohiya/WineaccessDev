package com.wineaccess.common;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"size","elements"})
public class DeleteVO<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7293257027825805506L;
	protected int size;
	
	@XmlElementWrapper(name="elements")
	@XmlElement(name="element")
	protected List<T> elements;
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
	public List<T> getElements() {
		return elements;
	}
	public void setElements(List<T> elements) {
		this.elements = elements;
		if(elements != null && !elements.isEmpty())
			setSize(elements.size());
	}
	
	
	
	
	

}
