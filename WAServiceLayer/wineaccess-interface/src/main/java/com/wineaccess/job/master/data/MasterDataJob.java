package com.wineaccess.job.master.data;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataRepository;
import com.wineaccess.data.model.common.MasterDataType;
import com.wineaccess.persistence.dao.GenericDAO;

public class MasterDataJob implements Runnable {
	
	private static Log logger = LogFactory.getLog(MasterDataJob.class);
	
	public static String MASTER_DATA_TYPE_FILE = System.getenv("WINEACCESS_HOME") + "/masterDataType.xml";
	private long lastModifiedTime = 0L;
	
	private void addUpdateMasterData(MasterDataJobPO masterDataJobPO, MasterDataType masterDataType) {
		boolean isGot = false;
		for (MasterData masterData : masterDataType.getMasterData()) {
			if (masterData.getName().equals(masterDataJobPO.getName())) {
				masterData.setName(masterDataJobPO.getName());
				MasterDataRepository.update(masterData);
				isGot = true;
				break;
			}
		}
		if (!isGot){
			MasterData masterData = new MasterData();
			masterData.setName(masterDataJobPO.getName());
			masterData.setMasterDataType(masterDataType);
			MasterDataRepository.save(masterData);
		}
	}
	
	@Override
	public void run() {
		do {
			try {
				File f = new File(MASTER_DATA_TYPE_FILE);
				long lastModified = f.lastModified();
				
				if (lastModified > lastModifiedTime) {
					JAXBContext context = JAXBContext.newInstance(MasterDataTypeJobPO.class);
					Unmarshaller un = context.createUnmarshaller();
					MasterDataTypeJobPO masterDataTypeJobPO = (MasterDataTypeJobPO) un.unmarshal(f);
					
					for (MasterDataTypeJobModel masterDataTypeJobModel : masterDataTypeJobPO.getAddUpdateMasterDataType()) {
						
						if (masterDataTypeJobModel.getName().isEmpty()) {
							logger.error("Invalid Data -> Master Data Name is Blank");
							continue;
						}
						
						if (!(masterDataTypeJobModel.getStatus().equals("true") || masterDataTypeJobModel.getStatus().equals("false"))) {
							logger.error("Invalid Data -> status could be either true or false");
							continue;
						}
						
						boolean status = Boolean.parseBoolean(masterDataTypeJobModel.getStatus()) ;
						MasterDataType masterDataType = new MasterDataType(masterDataTypeJobModel.getName(), masterDataTypeJobModel.getDescription(), status, masterDataTypeJobModel.getLabel());
						
						GenericDAO<MasterDataType> masterDataDAO = (GenericDAO<MasterDataType>)  CoreBeanFactory.getBean("genericDAO");
						List<MasterDataType> masterDataTypes = masterDataDAO.findByNamedQuery("MasterDataType.getMasterDataByName", new String [] {"name"}, masterDataType.getName());
						
						if (!masterDataTypes.isEmpty()) {
							masterDataTypes.get(0).setDescription(masterDataType.getDescription());
							masterDataTypes.get(0).setStatus(masterDataType.isStatus());
							masterDataTypes.get(0).setLabel(masterDataType.getLabel());
							masterDataDAO.update(masterDataTypes.get(0));
							masterDataType = masterDataTypes.get(0);
						} else {
							masterDataDAO.create(masterDataType);
						}
						
						for (MasterDataJobPO masterDataJobPO : masterDataTypeJobModel.getAddUpdateMasterData()) {
							addUpdateMasterData(masterDataJobPO, masterDataType);
						}
					}
				}
				lastModifiedTime = lastModified;
				Thread.sleep(15000);
			} catch(Exception ex) {
				logger.error(ex);
			}
		} while (true);
	}
}
