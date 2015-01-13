package com.wineaccess.orchestration.core.common.configuration;

/**
 * <p>
 * This class contains properties of CIG configurable classes.It is based on CIG
 * requirement.
 * <p>
 * 
 * @author jyoti.yadav@globallogic.com
 * 
 */
public class Property {

	/** name. */
	private String name;

	/** value. */
	private String value;
	

	/**
	 * Constructor.
	 * @param name : String
	 * @param value : String
	 */
	public Property(String name, String value){
		this.name = name;
		this.value = value;
	}
	
	/**
	 * <p>
	 * get name.
	 * <p>
	 * 
	 * @return returns name
	 */
	public String getName() {
		return name;
	}

	/**
	 * <p>
	 * get value.
	 * <p>
	 * 
	 * @return returns value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <p>
	 * Sets name to property.
	 * <p>
	 * 
	 * @param name : String
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <p>
	 * Sets value to property.
	 * <p>
	 * 
	 * @param value : String
	 * 
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
