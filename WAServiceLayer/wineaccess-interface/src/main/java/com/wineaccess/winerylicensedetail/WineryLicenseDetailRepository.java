package com.wineaccess.winerylicensedetail;

import java.util.List;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.catalog.WineryLicenseDetailModel;
import com.wineaccess.data.model.catalog.WineryModel;
import com.wineaccess.persistence.dao.GenericDAO;

public class WineryLicenseDetailRepository {

	@SuppressWarnings("unchecked")
	public static void save(WineryLicenseDetailModel wineryLicenseDetailModel) {
		GenericDAO<WineryLicenseDetailModel> genericDao = (GenericDAO<WineryLicenseDetailModel>) CoreBeanFactory
				.getBean("genericDAO");
		genericDao.create(wineryLicenseDetailModel);
	}

	@SuppressWarnings("unchecked")
	public static WineryLicenseDetailModel getWineryLicenseDetailById(Long id) {
		GenericDAO<WineryLicenseDetailModel> genericDao = (GenericDAO<WineryLicenseDetailModel>) CoreBeanFactory
				.getBean("genericDAO");
		List<WineryLicenseDetailModel> wineryLicenseDetailModels = genericDao
				.findByNamedQuery("WineryLicenseDetailModel.getById",
						new String[] { "id" }, id);
		if (wineryLicenseDetailModels != null
				&& wineryLicenseDetailModels.size() > 0)
			return wineryLicenseDetailModels.get(0);
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public static WineryLicenseDetailModel getWineryLicenseDetailByWinery(WineryModel winery) {
		GenericDAO<WineryLicenseDetailModel> genericDao = (GenericDAO<WineryLicenseDetailModel>) CoreBeanFactory
				.getBean("genericDAO");		
			
		List<WineryLicenseDetailModel> wineryLicenseDetailModels = genericDao
				.findByNamedQuery("WineryLicenseDetailModel.getByWineryId",
						new String[] { "winery" }, winery);
		if (wineryLicenseDetailModels != null
				&& wineryLicenseDetailModels.size() > 0)
			return wineryLicenseDetailModels.get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public static WineryLicenseDetailModel update(
			WineryLicenseDetailModel wineryLicenseDetailModel) {
		GenericDAO<WineryLicenseDetailModel> genericDao = (GenericDAO<WineryLicenseDetailModel>) CoreBeanFactory
				.getBean("genericDAO");
		return genericDao.update(wineryLicenseDetailModel);
	}

}
