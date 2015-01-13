package com.wineaccess.util.command;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StateListVO implements Serializable {
	
	private static final long serialVersionUID = 2447308669831397125L;

	@XmlElementWrapper(name = "stateList")
	@XmlElement(name = "state")
	private List<State> states = null;
	
	
	public StateListVO() {
		states = new ArrayList<State>();
	}
	
	
	public StateListVO(List<State> states) {
		super();
		this.states = states;
	}
	
	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}