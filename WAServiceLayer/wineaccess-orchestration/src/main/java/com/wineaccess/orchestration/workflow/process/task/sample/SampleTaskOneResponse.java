package com.wineaccess.orchestration.workflow.process.task.sample;

import java.io.Serializable;

/**
 * @author jyoti.yadav@globalligic.com
 */
public class SampleTaskOneResponse implements Serializable {

	private String name;
	private int age;

	public SampleTaskOneResponse() {
	}

	public SampleTaskOneResponse(String name, int age) {
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
