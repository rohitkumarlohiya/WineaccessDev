package com.wineaccess.data.model.catalog;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;

public class ActiveImporterFieldBridgeById implements FieldBridge {

	@Override
	public void set(String name, Object importerObject, Document luceneDocument, LuceneOptions luceneOption) {
		ImporterModel importerModel = (ImporterModel) importerObject;
		if (importerModel != null) {
			Field field = new Field(name, String.valueOf(importerModel.getId()), luceneOption.getStore(), luceneOption.getIndex(), luceneOption.getTermVector());
			luceneDocument.add(field);
		} else {
			Field field = new Field(name, "", luceneOption.getStore(), luceneOption.getIndex(), luceneOption.getTermVector());
			luceneDocument.add(field);
		}
	}
}
