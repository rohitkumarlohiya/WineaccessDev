package com.wineaccess.importer;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.wineaccess.command.BasePO;

@XmlRootElement(name="deleteImporter")
public class DeleteImporterPO extends BasePO implements Serializable{

	private static final long serialVersionUID = -5533955929788401899L;
	
	private List<Long> importerIds;
	private Boolean isForceDelete;
	
	public List<Long> getImporterIds() {
		return importerIds;
	}

	public void setImporterIds(List<Long> importerIds) {
		this.importerIds = importerIds;
	}

	public Boolean getIsForceDelete() {
		return isForceDelete;
	}

	public void setIsForceDelete(Boolean isForceDelete) {
		this.isForceDelete = isForceDelete;
	}
}
