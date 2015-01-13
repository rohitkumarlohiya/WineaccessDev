package com.wineaccess.data.model.catalog.wine;

import java.io.Serializable;

import com.wineaccess.data.model.catalog.ImporterModel;

public class TestModel implements Serializable {

	boolean isImported = false;
	ImporterModel activeImporter = null;
	
	public TestModel(){
	}

	public TestModel(boolean isImported, ImporterModel activeImporter) {
		this.isImported = isImported;
		this.activeImporter = activeImporter;
	}

	public boolean isImported() {
		return isImported;
	}

	public void setImported(boolean isImported) {
		this.isImported = isImported;
	}

	public ImporterModel getActiveImporter() {
		return activeImporter;
	}

	public void setActiveImporter(ImporterModel activeImporter) {
		this.activeImporter = activeImporter;
	}
}
