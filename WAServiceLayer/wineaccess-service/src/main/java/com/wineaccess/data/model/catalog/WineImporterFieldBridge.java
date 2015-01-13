package com.wineaccess.data.model.catalog;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;

import com.wineaccess.data.model.catalog.wine.TestModel;

public class WineImporterFieldBridge implements FieldBridge {

	@Override
	public void set(String name, Object model, Document luceneDocument, LuceneOptions luceneOption) {
		
		if (name.equals("importerIdentifier")) {
			if (model != null) {
				Field field = new Field(name, String.valueOf(model), luceneOption.getStore(), luceneOption.getIndex(), luceneOption.getTermVector());
				luceneDocument.add(field);
			} else {
				Field field = new Field(name, "0", luceneOption.getStore(), luceneOption.getIndex(), luceneOption.getTermVector());
				luceneDocument.add(field);
			}
			return;
		}
		
		if (model instanceof Long) {
			Field field = new Field(name, String.valueOf(model), luceneOption.getStore(), luceneOption.getIndex(), luceneOption.getTermVector());
			luceneDocument.add(field);
		} else {
			TestModel testModel = (TestModel) model;
			if (testModel != null) {
				
				if (testModel.isImported()) {
					if (name.equals("region")) {
						Field field = new Field(name, testModel.getActiveImporter().getCountryId().getCountryCode().toLowerCase(), luceneOption.getStore(), luceneOption.getIndex(), luceneOption.getTermVector());
						luceneDocument.add(field);
					} else {
						Field field = new Field(name, testModel.getActiveImporter().getImporterName().toLowerCase(), luceneOption.getStore(), luceneOption.getIndex(), luceneOption.getTermVector());
						luceneDocument.add(field);
					}
				} 
			} else {
				if (name.equals("region")) {
					Field field = new Field(name, testModel.getActiveImporter().getCountryId().getCountryCode().toLowerCase(), luceneOption.getStore(), luceneOption.getIndex(), luceneOption.getTermVector());
					luceneDocument.add(field);
				} else {
					Field field = new Field(name, testModel.getActiveImporter().getImporterName().toLowerCase(), luceneOption.getStore(), luceneOption.getIndex(), luceneOption.getTermVector());
					luceneDocument.add(field);
				}
			} 
		} 
	}
}
