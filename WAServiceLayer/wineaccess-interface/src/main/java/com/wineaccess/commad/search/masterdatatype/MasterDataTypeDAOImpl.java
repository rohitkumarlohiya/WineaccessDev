package com.wineaccess.commad.search.masterdatatype;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.queryParser.ParseException;

import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataType;
import com.wineaccess.data.model.common.MasterDataTypeRepository;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.persistence.dao.impl.JPAGenericDAOImpl;
import com.wineaccess.security.masterdatatype.MasterDataCustomModel;
import com.wineaccess.security.masterdatatype.MasterDataTypeCustomModel;
import com.wineaccess.user.activity.log.UserServiceModel;

/**
 * 
 * @author rohit.lohiya
 * 
 * @param <T>
 */
public class MasterDataTypeDAOImpl<T extends Persistent> extends
		JPAGenericDAOImpl<T> implements MasterDataTypeDAO<T> {

	@PersistenceContext
	EntityManager em;

	String[] searchFields = new String[] { "id","name", "description" };
	
	

	@Override
	public MasterDataTypeSearchVO getNormalSearchMasterDataType(String keyword,
			int offSet, int limit, String sortBy, int order, List<String> exclusionList) throws ParseException {
		
		String fieldName = "id";
		
		if (sortBy.equals("name")) {
			fieldName = "name";
		} else if (sortBy.equals("description")) {
			fieldName = "description";
		}else if(sortBy.equals("modifiedBy")){
			fieldName = "modifiedBy";
		}else if(sortBy.equals("modifyDate")){
			fieldName = "modifiedDate";
		}else if (sortBy.equals("label")) {
			fieldName = "label";
		}

		int size = MasterDataTypeRepository.countRecordsForQuery(keyword, fieldName,order, exclusionList);

		List<MasterDataType> masterDataTypes = MasterDataTypeRepository.getMasterDataTypes(keyword, fieldName, order, offSet-1, limit, exclusionList);

		List<MasterDataTypeCustomModel> customListVOs = new ArrayList<MasterDataTypeCustomModel>();

		for (MasterDataType masterDataType : masterDataTypes) {

			List<MasterDataCustomModel> masterDataCustomVOs = new ArrayList<MasterDataCustomModel>();
			int masterDataCount = 0;
			if(masterDataType.getMasterData() != null && !masterDataType.getMasterData().isEmpty()){
				masterDataCount = masterDataType.getMasterData().size();
			}
			Set<MasterData> masterDatas = masterDataType.getMasterData();

			UserModel userModel = UserRepository.findUserByUserId(masterDataType.getModifiedBy());
			UserServiceModel userServiceModel = null;
			if(userModel != null)
			{
				userServiceModel = new UserServiceModel(userModel.getId(),userModel.getFirstName(),userModel.getLastName(),userModel.getEmail());
			}

			MasterDataTypeCustomModel masterDataTypeCustomVO = new MasterDataTypeCustomModel(
					masterDataType.getId(), masterDataType.getName(),
					masterDataType.getDescription(), masterDataType.isStatus(),
					masterDataCount,userServiceModel,masterDataType.getModifiedDate(), masterDataType.getLabel());
			customListVOs.add(masterDataTypeCustomVO);
		}

		MasterDataTypeSearchVO masterDataTypeSearchVO = new MasterDataTypeSearchVO(
				size, offSet, limit, keyword, customListVOs, MasterDataTypeRepository.countAllRecords(exclusionList));

		return masterDataTypeSearchVO;
	}

}
