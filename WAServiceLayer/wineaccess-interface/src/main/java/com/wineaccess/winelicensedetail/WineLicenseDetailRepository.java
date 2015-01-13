package com.wineaccess.winelicensedetail;

import java.util.List;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.catalog.wine.WineLicenseDetailModel;
import com.wineaccess.data.model.catalog.wine.WineModel;
import com.wineaccess.persistence.dao.GenericDAO;

public class WineLicenseDetailRepository {

	@SuppressWarnings("unchecked")
	public static void save(WineLicenseDetailModel wineLicenseDetailModel) {
		GenericDAO<WineLicenseDetailModel> genericDao = (GenericDAO<WineLicenseDetailModel>) CoreBeanFactory
				.getBean("genericDAO");
		genericDao.create(wineLicenseDetailModel);
	}

	@SuppressWarnings("unchecked")
	public static WineLicenseDetailModel getWineLicenseDetailById(Long id) {
		GenericDAO<WineLicenseDetailModel> genericDao = (GenericDAO<WineLicenseDetailModel>) CoreBeanFactory
				.getBean("genericDAO");
		List<WineLicenseDetailModel> wineLicenseDetailModels = genericDao
				.findByNamedQuery("WineLicenseDetailModel.getById",
						new String[] { "id" }, id);
		if (wineLicenseDetailModels != null
				&& wineLicenseDetailModels.size() > 0)
			return wineLicenseDetailModels.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public static WineLicenseDetailModel getWineLicenseDetailByWine(WineModel wine) {
		GenericDAO<WineLicenseDetailModel> genericDao = (GenericDAO<WineLicenseDetailModel>) CoreBeanFactory
				.getBean("genericDAO");		
			
		List<WineLicenseDetailModel> wineLicenseDetailModels = genericDao
				.findByNamedQuery("WineLicenseDetailModel.getByWineId",
						new String[] { "wine" }, wine);
		if (wineLicenseDetailModels != null
				&& wineLicenseDetailModels.size() > 0)
			return wineLicenseDetailModels.get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public static WineLicenseDetailModel update(
			WineLicenseDetailModel wineLicenseDetailModel) {
		GenericDAO<WineLicenseDetailModel> genericDao = (GenericDAO<WineLicenseDetailModel>) CoreBeanFactory
				.getBean("genericDAO");
		return genericDao.update(wineLicenseDetailModel);
	}

}
