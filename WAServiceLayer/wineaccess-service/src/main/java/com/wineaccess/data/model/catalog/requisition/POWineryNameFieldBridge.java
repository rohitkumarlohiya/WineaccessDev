package com.wineaccess.data.model.catalog.requisition;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;

import com.wineaccess.data.model.catalog.WineryModel;

public class POWineryNameFieldBridge implements FieldBridge {

	@Override
	public void set(String name, Object wienryObject, Document luceneDocument, LuceneOptions luceneOption) {
		WineryModel wienryModel = (WineryModel) wienryObject;
		if (wienryModel != null) {
			Field field = new Field(name, wienryModel.getWineryName(), luceneOption.getStore(), luceneOption.getIndex(), luceneOption.getTermVector());
			luceneDocument.add(field);
		}
	}

}
