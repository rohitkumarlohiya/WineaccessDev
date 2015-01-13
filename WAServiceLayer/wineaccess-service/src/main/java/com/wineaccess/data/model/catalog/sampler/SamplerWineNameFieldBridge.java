package com.wineaccess.data.model.catalog.sampler;

import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.catalog.product.ProductItemModel;
import com.wineaccess.data.model.catalog.wine.WineModel;
import com.wineaccess.persistence.dao.GenericDAO;

public class SamplerWineNameFieldBridge implements FieldBridge {

	@Override
	public void set(String name, Object productItemObject, Document luceneDocument, LuceneOptions luceneOption) {
		ProductItemModel productItemModel = (ProductItemModel) productItemObject;
		if (productItemModel != null) {
		
			GenericDAO<WineModel> genericDao = (GenericDAO<WineModel>) CoreBeanFactory.getBean("genericDAO");
			List<WineModel> wineModels = genericDao.findByNamedQuery("WineModel.getById", new String[] { "id" }, productItemModel.getItemId());
			if (wineModels != null && wineModels.size() > 0) {
				Field field = new Field(name, wineModels.get(0).getWineFullName(), luceneOption.getStore(), luceneOption.getIndex(), luceneOption.getTermVector());
				luceneDocument.add(field);
			}
		} else {
			Field field = new Field(name, "", luceneOption.getStore(), luceneOption.getIndex(), luceneOption.getTermVector());
			luceneDocument.add(field);
		}
	}
}
