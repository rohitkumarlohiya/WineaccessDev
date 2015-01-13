package com.wineaccess.job.master.data;

import java.io.Serializable;
import java.util.List;

public class MasterDataTypeJobModel implements Serializable {

	private String name;

	private String description;

	private String status;
	
	private String label;
	
	private List<MasterDataJobPO> addUpdateMasterData;
	
	public MasterDataTypeJobModel(){
	}
	
	public MasterDataTypeJobModel(String name, String description, String status, String label){
		this.name = name;
		this.description = description;
		this.status = status;
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<MasterDataJobPO> getAddUpdateMasterData() {
		return addUpdateMasterData;
	}

	public void setAddUpdateMasterData(List<MasterDataJobPO> addUpdateMasterData) {
		this.addUpdateMasterData = addUpdateMasterData;
	}
}
