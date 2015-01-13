package com.sendgrid.model;

import java.util.ArrayList;
import java.util.List;

public class MallingList {
	
	List<String> toList;
	List<String> ccList;
	List<String> bccList;
	
	public MallingList(){
		toList = new ArrayList<String>();
		ccList = new ArrayList<String>();
		bccList = new ArrayList<String>();
	}
	
	public List<String> getToList() {
		return toList;
	}
	public void setToList(List<String> toList) {
		this.toList = toList;
	}
	public List<String> getCcList() {
		return ccList;
	}
	public void setCcList(List<String> ccList) {
		this.ccList = ccList;
	}
	public List<String> getBccList() {
		return bccList;
	}
	public void setBccList(List<String> bccList) {
		this.bccList = bccList;
	}
	
	

}
