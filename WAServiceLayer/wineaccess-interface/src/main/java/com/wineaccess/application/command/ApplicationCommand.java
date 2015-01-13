package com.wineaccess.application.command;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wineaccess.command.BaseCommand;

@Path("/{version}/invoke/{command}")
public class ApplicationCommand extends BaseCommand	{

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public javax.ws.rs.core.Response invokeCommand(@HeaderParam("X-API-KEY") String apiKey, @HeaderParam("API_ACCESS_CODE") String apiAccessCode, 
	    @PathParam("version") String version, @PathParam("command") String commandName, String commandParameter ) {

	if (!AppCommand.ApiAccess.name().equals(commandName)){
	    javax.ws.rs.core.Response errorResponse = validateApiKeyAndVersion(apiKey, version, apiAccessCode, commandName, commandParameter);
	    if (errorResponse != null) { 
		return errorResponse;
	    }
	} else {
	    if (!getDataRepositoryManager().validateApiKeyVersion(apiKey, version)) {
		return javax.ws.rs.core.Response.ok(prepareFailureApiKeyResponse()).build();
	    }
	}
	return processRequest(commandName, version, commandParameter);
    }

    public enum AppCommand {
	Login, /** http://localhost:8181/wineaccess/apis/v1/invoke/LOGINCMD **/

	SearchMasterData, /** http://<host:port>/wineaccess/apis/{version}/search/masterdata?keyword=wine KEYWORD SEARCH */

	SearchMasterDataByTypeId, /** http://<host:port>/wineaccess/apis/{version}/search/masterdata/{masterdatatypeid}?keyword=wine*/

	SearchMasterDataType, /** http://<host:port>/wineaccess/apis/v1/search/masterdatatype?keyword=vintage KEYWORD SEARCH */

	SearchUser, /** http://<host:port>/wineaccess/apis/{version}/search/users?keyword=Admin&sortBy=id **/

	UpdateUserPasswordById,  /** http://<host:port>/wineaccess/apis/v1/user/profile/update/password/userId **/

	UpdateUserPasswordByEmail, /** http://<host:port>/wineaccess/apis/v1/user/profile/reset/password/email */

	UserListById, /** http://<host:port>/wineaccess/apis/v1/user/profile/list/id/{userId} */

	UserListByEmail, /** /list/email/{email} */

	UpdateUserDetailById, /** http://<host:port>/wineaccess/apis/v1/user/profile/update/profile/userId */

	UpdateUserDetailByEmail, /** /update/profile/email */

	ResetPasswordByEmail, /** http://<host:port>/wineaccess/apis/v1/user/profile/reset/password/email*/

	ListEmailTemplate,  /** /{version}/emailtemplate */

	ListEmailTemplateById,/** /{version}/emailtemplate/{id} */

	SearchEmailTemplate, 

	AddEmailTemplate, 

	UpdateEmailTemplateById, 

	EmailTemplateMultipleDelete, 

	CloneEmailTemplateById,

	ListEmailTemplateType, 

	EmailTemplateTypeById, 

	EmailTemplateTypeByName, 

	SearchEmailTemplateType, 

	SSOLogin,

	Signup, 

	Responsys,

	LoginHistoryById,

	LoginHistoryByEmail, 

	Logout, 

	AddMasterData, 

	ListMasterData, 

	MasterDataById,

	MasterDataLastUpdated,

	UpdateMasterDataById, 

	DeleteMasterDataById,

	MultipleDeleteMasterData, 

	ListMasterDataTypes, 

	MasterDataTypeById,

	MasterDataTypeByName,

	SearchNewsLetter, 

	ListNewsLetterById, 

	AddNewsLetter, 

	UpdateNewsLetter, 

	DeleteNewsLetter, 

	TemplatePlaceHoldersList,

	UserActivation, 

	UserSessionSummary, 

	GetUserDetailForSession,

	GetSessionDetailForUserBySessionId,

	AddComment, 

	ViewCommentById, 

	EditComment, 

	ListCommentByUserId, 

	DeleteCommentById, 

	MultiDeleteCommentById,

	ListCity, 

	ListCityById,

	ListStateById,

	ListCountry, 

	GetCountryById,

	ListState, 

	GetStateById,

	GetStateByCountryId,

	ApiAccess,

	ResendActivationMail,

	ListUserEmailLog,

	ListUserEmailLogSearch,

	CreateUser,

	UpdateUser,

	CloneUser,

	EnableUser,

	DisableUser,

	DeleteUser,

	DeleteUserAddress,

	DeleteUserCreditCard,

	GetUserDetailById,

	MergeUser,

	ResetUserPassword,

	AddUserAddress,

	UpdateUserAddress,

	UserAddressDetail,

	AddUserCreditCard,

	UpdateUserCreditCard,

	UserCreditCardDetail,

	AddImporter,

	UpdateImporter,

	DeleteImporter,

	AddWinery,

	UpdateWinery,

	ViewImporter,

	AddWineryImporterAddress,

	EditWineryImporterAddress,

	ViewWineryImporterAddress,

	DeleteWineryImporterAddress,

	ListWineryImporterAddress,

	ViewWinery,

	SearchImporter,

	DeleteWinery,

	SearchWinery,

	AddContact,

	UpdateContact,

	ViewContactDetail,

	ViewContacts,

	ListContacts,

	DeleteContacts,

	ForgetPasswordEmail,

	UpdateForgotPassword,

	AddWine,

	UpdateWine,

	ViewWine,

	ListWine,

	DeleteWine,

	AddWineLogistic,

	EditWineLogistic,

	ViewWineLogistic,

	CreateUpdateWineryOWS,

	AddWinePermit,

	ViewWineryOWS,

	AddWineryLicenseDetail,

	UpdateWineryLicenseDetail,

	ViewWineryLicenseDetail,

	UpdateWineOWS,

	ViewWineOWS, 

	ViewWinePermit,

	AddWineryPermit,

	ViewWineryPermit,

	ViewWineLicenseDetail,

	UpdateWineLicenseDetail,

	GetCreditCardsOfUser, 

	GetAddressesOfUser,		

	SearchRequisition,

	AddRequisitionForPOWT,
	
	AddRequisitionForIT,
	
	ViewRequisition,

	EnableDisableWinery,

	ChangeWineStatus,

	AddWarehouse,

	AddDistributionCentre, 

	AddWineToRequisition,
	
	EditWineToRequisition,

	UpdateDistributionCentre,

	UpdateWarehouse,

	ViewWarehouse,

	ViewDistributionCentre,

	DeleteDistributionCentre,

	ListDistributionCentre,

	ListWarehouse,

	DeleteWarehouse, 
	
	ListWineInRequisition,

	UpdateRequisition,
	
	AddSampler,
	
	ViewSampler,
	
	SearchSampler,
	
	RemoveWineFromRequisition,

	ViewSamplerLogisticsDetail,
	
	ViewSamplerComplienceDetail,

	EditSamplerWine,
	
	UpdateSampler,
	AddSamplerProduct,

	EnableDisableImporter,

	RemoveWineFromSampler,
	
	ListSamplerProduct,
	
	CreateUserPassword,
	
	UpdateRequisitionForPOWT,
	
	UpdateRequisitionForIT,
	
	DeleteRequisition,
	
	SendEmailToWinery
  }
}
