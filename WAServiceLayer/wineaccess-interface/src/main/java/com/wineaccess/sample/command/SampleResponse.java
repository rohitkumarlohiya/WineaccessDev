package com.wineaccess.sample.command;

import java.io.Serializable;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class SampleResponse implements Serializable{

	private String name;
	private int age;
	
	public SampleResponse() {
	}
	
	public SampleResponse(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
