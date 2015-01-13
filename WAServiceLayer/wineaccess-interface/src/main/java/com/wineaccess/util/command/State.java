package com.wineaccess.util.command;

	
import java.io.Serializable;

public class State implements Serializable {
	
	private static final long serialVersionUID = 2741338308290178586L;
	private String name;
	private Long id;
	private String stateCode;
	
	public State(){
		
	}

	public State(Long id, String stateCode, String stateName) {
		this.id = id;
		this.stateCode = stateCode;
		this.name=stateName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

}
