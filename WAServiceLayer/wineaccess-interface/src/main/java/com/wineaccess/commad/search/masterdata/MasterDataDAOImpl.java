package com.wineaccess.commad.search.masterdata;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;

import com.wineaccess.ApplicationUtil.ApplicationUtils;
import com.wineaccess.constants.WineaccessErrorCodes;
import com.wineaccess.data.model.common.MasterData;
import com.wineaccess.data.model.common.MasterDataRepository;
import com.wineaccess.data.model.common.MasterDataType;
import com.wineaccess.data.model.common.MasterDataTypeRepository;
import com.wineaccess.data.model.user.Persistent;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.data.model.user.UserRepository;
import com.wineaccess.persistence.dao.impl.JPAGenericDAOImpl;
import com.wineaccess.response.Response;
import com.wineaccess.security.masterdata.MasterDataCustModel;
import com.wineaccess.security.masterdata.MasterDataTypeResponse;
import com.wineaccess.security.masterdata.SearchMasterDataCustModel;
import com.wineaccess.user.activity.log.UserServiceModel;

/**
 * 
 * @author rohit.lohiya
 * 
 * @param <T>
 */
public class MasterDataDAOImpl<T extends Persistent> extends
		JPAGenericDAOImpl<T> implements MasterDataDAO<T> {

	
	@Override
	public Response getNormalSearchMasterData(String keyword, int offSet, int limit, String sortBy, int order, String name) throws ParseException {
		
		
		String fieldName = "id";
		
		if (sortBy.equals("name")) {
			fieldName = "name";
		}else if(sortBy.equals("modifiedBy")){
			fieldName = "modifiedBy";
		}else if(sortBy.equals("modifiedDate")){
			fieldName = "modifiedDate";
		}
		
		MasterDataType masterDataType = null;
		
		if (name != null && name.isEmpty()) {
			return  ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.EMPTY_MASTERDATATYPE_ID,WineaccessErrorCodes.SystemErrorCode.EMPTY_MASTERDATATYPE_ID_TEXT, 200);
		}
		
		if(name != null){
			masterDataType = MasterDataTypeRepository.getMasterDataTypeByName(name);
			if(masterDataType == null){
				return  ApplicationUtils.errorMessageGenerate(WineaccessErrorCodes.SystemErrorCode.INVALID_MASTERDATATYPE_ID,WineaccessErrorCodes.SystemErrorCode.INVALID_MASTERDATATYPE_ID_TEXT, 200);
			}
		}
		int totalCount = 0;
		int size = MasterDataRepository.countRecordsForQuery(keyword, fieldName, order, name, masterDataType);
		
		List<MasterData> masterDatas = MasterDataRepository.getMasterData(keyword, fieldName, order, offSet-1, limit, name, masterDataType);
		if(keyword != null && keyword.isEmpty()){
			totalCount = size;
		}else{
			totalCount = MasterDataRepository.countAllRecords();
		}
		if(masterDataType != null){
			if(masterDataType.getMasterData() != null)
			totalCount = masterDataType.getMasterData().size();
		}

		Response response = null;
		
		if(masterDataType != null){
			List<SearchMasterDataCustModel> masterDataCustModels = new ArrayList<SearchMasterDataCustModel>();

			for (MasterData masterData : masterDatas) {
				
				UserModel userModel = UserRepository.findUserByUserId(masterData.getModifiedBy());
				UserServiceModel userServiceModel = null;
				if(userModel != null)
				{
					userServiceModel = new UserServiceModel(userModel.getId(),userModel.getFirstName(),userModel.getLastName(),userModel.getEmail());
				}
				
				SearchMasterDataCustModel masterDataCustomVO = new SearchMasterDataCustModel(
						masterData.getId(), masterData.getName(),userServiceModel,masterData.getModifiedDate());
				masterDataCustModels.add(masterDataCustomVO);
				
			}
			
			MasterDataSearchByIdVO masterDataSearchVO = new MasterDataSearchByIdVO(size,
					offSet, limit, keyword, masterDataCustModels,totalCount,new MasterDataTypeResponse(masterDataType.getId(),masterDataType.getName(),masterDataType.getDescription(), masterDataType.getLabel()));
	
			response = new com.wineaccess.response.SuccessResponse(masterDataSearchVO, 200);
			
			
		}else{
			
			List<MasterDataCustModel> masterDataCustModels = new ArrayList<MasterDataCustModel>();

			for (MasterData masterData : masterDatas) {
				
				UserModel userModel = UserRepository.findUserByUserId(masterData.getModifiedBy());
				UserServiceModel userServiceModel = null;
				if(userModel != null)
				{
					userServiceModel = new UserServiceModel(userModel.getId(),userModel.getFirstName(),userModel.getLastName(),userModel.getEmail());
				}
				
				MasterDataCustModel masterDataCustomVO = new MasterDataCustModel(
						masterData.getId(), masterData.getName(),masterData.getMasterDataType(),userServiceModel,masterData.getModifiedDate(), masterData.getMasterDataType().getLabel());
				masterDataCustModels.add(masterDataCustomVO);
				
			}
			
			MasterDataSearchVO masterDataSearchVO = new MasterDataSearchVO(size,
					offSet, limit, keyword, masterDataCustModels,totalCount);
	
			response = new com.wineaccess.response.SuccessResponse(masterDataSearchVO, 200);
			
		}
		

		

		
		
		///;
		return response;
		//return masterDataSearchVO;
	}

}
