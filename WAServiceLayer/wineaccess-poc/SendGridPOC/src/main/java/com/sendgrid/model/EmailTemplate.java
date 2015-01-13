package com.sendgrid.model;

import java.util.List;

public class EmailTemplate {
	
	MallingList maillingList;
	String from;
	String subject;
	String bodyText;
	
	public EmailTemplate(){
		maillingList = new MallingList();
	}
	
	public MallingList getMaillingList() {
		return maillingList;
	}
	public void setMaillingList(MallingList maillingList) {
		this.maillingList = maillingList;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBodyText() {
		return bodyText;
	}
	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}
	
	public static void main(String[] args){
		
	}

}
