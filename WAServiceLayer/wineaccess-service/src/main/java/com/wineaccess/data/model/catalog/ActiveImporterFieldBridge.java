package com.wineaccess.data.model.catalog;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;

public class ActiveImporterFieldBridge implements FieldBridge {

	@Override
	public void set(String name, Object importerObject, Document luceneDocument, LuceneOptions luceneOption) {
		if (importerObject instanceof ImporterModel) {
			ImporterModel importerModel = (ImporterModel) importerObject;
			if (name.equals("activeImporterRegion")) {
				Field field = new Field(name, importerModel.getCountryId().getCountryCode(), luceneOption.getStore(), luceneOption.getIndex(), luceneOption.getTermVector());
				luceneDocument.add(field);
			} else {
				Field field = new Field(name, importerModel.getImporterName(), luceneOption.getStore(), luceneOption.getIndex(), luceneOption.getTermVector());
				luceneDocument.add(field);
			}
		} else if (String.valueOf(importerObject).isEmpty()) {
			Field field = new Field(name, String.valueOf("0"), luceneOption.getStore(), luceneOption.getIndex(), luceneOption.getTermVector());
			luceneDocument.add(field);
		}
	}
}
