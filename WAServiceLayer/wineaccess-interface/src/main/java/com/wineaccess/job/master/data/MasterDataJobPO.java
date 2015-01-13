package com.wineaccess.job.master.data;

import java.io.Serializable;

public class MasterDataJobPO implements Serializable {

	private String name;
	
	
	public MasterDataJobPO(){
	}
	
	public MasterDataJobPO(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
