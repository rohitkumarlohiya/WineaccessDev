package com.wineaccess.response;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.wineaccess.commad.search.masterdata.MasterDataSearchByIdVO;
import com.wineaccess.commad.search.masterdata.MasterDataSearchVO;
import com.wineaccess.commad.search.masterdatatype.MasterDataTypeSearchVO;
import com.wineaccess.commad.search.users.SearchCriteriaPO;
import com.wineaccess.commad.search.users.UserAdavanceSearchVO;
import com.wineaccess.commad.search.users.UserSearchVO;
import com.wineaccess.command.search.importer.ImporterSearchVO;
import com.wineaccess.command.search.po.POSearchVO;
import com.wineaccess.command.search.sampler.SamplerAdvSearchVO;
import com.wineaccess.command.search.sampler.SamplerSearchVO;
import com.wineaccess.command.search.winery.WinerySearchVO;
import com.wineaccess.data.model.profile.CreateUserPasswordVO;
import com.wineaccess.data.model.user.UserModel;
import com.wineaccess.distributioncentre.AddDistributionCentreVO;
import com.wineaccess.distributioncentre.DistributionCentreListingVO;
import com.wineaccess.distributioncentre.UpdateDistributionCentreVO;
import com.wineaccess.distributioncentre.ViewDistributionCentreVO;
import com.wineaccess.emailtemplate.EmailTemplateAddVO;
import com.wineaccess.emailtemplate.EmailTemplateBulkDeleteVO;
import com.wineaccess.emailtemplate.EmailTemplateCloneVO;
import com.wineaccess.emailtemplate.EmailTemplateGetByIdVO;
import com.wineaccess.emailtemplate.EmailTemplateListVO;
import com.wineaccess.emailtemplate.EmailTemplateSearchVO;
import com.wineaccess.emailtemplate.EmailTemplateUpdateVO;
import com.wineaccess.emailtemplatetype.EmailTemplateTypeGetByIdVO;
import com.wineaccess.emailtemplatetype.EmailTemplateTypeListVO;
import com.wineaccess.emailtemplatetype.EmailTemplateTypeSearch;
import com.wineaccess.emailtemplatetype.EmailTemplateTypeSearchPO;
import com.wineaccess.emailtemplatetype.EmailTemplateTypeSearchVO;
import com.wineaccess.importer.DeleteImporterVO;
import com.wineaccess.importer.ImporterEnableDisableVO;
import com.wineaccess.importer.ViewImporterVO;
import com.wineaccess.orders.requisition.AddRequisitionVO;
import com.wineaccess.orders.requisition.AddWineToRequisitionVO;
import com.wineaccess.orders.requisition.DeleteRequisitionVO;
import com.wineaccess.orders.requisition.EditRequisitionVO;
import com.wineaccess.orders.requisition.ListWineInRequisitionVO;
import com.wineaccess.orders.requisition.RemoveWineFromRequisitionVO;
import com.wineaccess.orders.requisition.RequisitionDetails;
import com.wineaccess.orders.requisition.ViewRequisitionVO;
import com.wineaccess.registration.RegistrationSSOVO;
import com.wineaccess.registration.SignupVO;
import com.wineaccess.sample.command.UserVO;
import com.wineaccess.sampler.AddSamplerProductVO;
import com.wineaccess.sampler.AddSamplerVO;
import com.wineaccess.sampler.DeleteSamplerProductVO;
import com.wineaccess.sampler.EditSamplerWineVO;
import com.wineaccess.sampler.ListSamplerProductVO;
import com.wineaccess.sampler.ProductDetail;
import com.wineaccess.sampler.SamplerProductDetails;
import com.wineaccess.sampler.UpdateSamplerVO;
import com.wineaccess.sampler.ViewSamplerComplienceDetailVO;
import com.wineaccess.sampler.ViewSamplerLogisticsDetailVO;
import com.wineaccess.sampler.ViewSamplerVO;
import com.wineaccess.security.login.LoginVO;
import com.wineaccess.security.login.history.LoginHistoryVO;
import com.wineaccess.security.masterdata.MasterDataAddVO;
import com.wineaccess.security.masterdata.MasterDataDeleteVO;
import com.wineaccess.security.masterdata.MasterDataGetByIdVO;
import com.wineaccess.security.masterdata.MasterDataListVO;
import com.wineaccess.security.masterdata.MasterDataMultipleDeleteVO;
import com.wineaccess.security.masterdata.MasterDataUpdateVO;
import com.wineaccess.security.masterdatatype.MasterDataTypeGetByIdVO;
import com.wineaccess.security.masterdatatype.MasterDataTypeListVO;
import com.wineaccess.service.newsletter.NewsletterDeleteVO;
import com.wineaccess.service.newsletter.NewsletterSearchPO;
import com.wineaccess.service.newsletter.NewsletterSearchVO;
import com.wineaccess.service.newsletter.NewsletterVOList;
import com.wineaccess.service.user.preference.UserPreferenceVO;
import com.wineaccess.user.activation.ResendActivationMailVO;
import com.wineaccess.user.activation.UserActivationVO;
import com.wineaccess.user.activity.log.TokenVO;
import com.wineaccess.user.activity.log.UserServiceModel;
import com.wineaccess.user.activity.log.UserSessionVO;
import com.wineaccess.user.comments.UserCommentAddVO;
import com.wineaccess.user.comments.UserCommentDeleteVO;
import com.wineaccess.user.comments.UserCommentGetByIdVO;
import com.wineaccess.user.comments.UserCommentListVO;
import com.wineaccess.user.comments.UserCommentMultiDeleteVO;
import com.wineaccess.user.comments.UserCommentsUpdateVO;
import com.wineaccess.useremaillog.UserEmailLogListVO;
import com.wineaccess.useremaillog.UserEmailLogSearchVO;
import com.wineaccess.usermanagement.AddressVO;
import com.wineaccess.usermanagement.CreditCardVO;
import com.wineaccess.usermanagement.DeleteComponentVO;
import com.wineaccess.usermanagement.DeleteUserVO;
import com.wineaccess.usermanagement.MergeUserVO;
import com.wineaccess.usermanagement.ModifystatusVO;
import com.wineaccess.usermanagement.UserAddressesListVO;
import com.wineaccess.usermanagement.UserCreditCardsListVO;
import com.wineaccess.usermanagement.UserDetailVO;
import com.wineaccess.usermanagement.UserManagementVO;
import com.wineaccess.util.command.CityListVO;
import com.wineaccess.util.command.CountryListVO;
import com.wineaccess.util.command.StateListVO;
import com.wineaccess.warehouse.AddWarehouseVO;
import com.wineaccess.warehouse.ListWarehouseVO;
import com.wineaccess.warehouse.WarehouseDetails;
import com.wineaccess.wine.ViewWineLogisticVO;
import com.wineaccess.wine.ViewWineVO;
import com.wineaccess.wine.WineAdvanceSearchVO;
import com.wineaccess.wine.WineBasicSearchVO;
import com.wineaccess.wine.WineDeleteVO;
import com.wineaccess.wine.WineLogisticBasicVO;
import com.wineaccess.wine.WineSearchVO;
import com.wineaccess.wine.WineVO;
import com.wineaccess.wineOWS.ViewWineOwsVO;
import com.wineaccess.winelicensedetail.WineLicenseDetailVO;
import com.wineaccess.winelicensedetail.WineLicenseDetailViewVO;
import com.wineaccess.winepermit.WinePermitAddVO;
import com.wineaccess.winepermit.WinePermitDetailVO;
import com.wineaccess.winery.ViewWineryVO;
import com.wineaccess.winery.WineryBulkDeleteVO;
import com.wineaccess.winery.WineryDeletePO;
import com.wineaccess.winery.WineryEnableDisableVO;
import com.wineaccess.winery.WineryPO;
import com.wineaccess.winery.WineryUpdatePO;
import com.wineaccess.wineryOWS.AddUpdateWineryOwsVO;
import com.wineaccess.wineryOWS.ViewWineryOwsVO;
import com.wineaccess.wineryimporter.WineryImporterAddressBasicVO;
import com.wineaccess.winerylicensedetail.WineryLicenseDetailVO;
import com.wineaccess.winerylicensedetail.WineryLicenseDetailViewVO;
import com.wineaccess.winerypermit.WineryPermitAddVO;
import com.wineaccess.winerypermit.WineryPermitDetailVO;
/**
 * @author jyoti.yadav@globallogic.com
 */
@XmlRootElement
@XmlSeeAlso({LoginVO.class, UserModel.class, UserVO.class, UserAdavanceSearchVO.class,SearchCriteriaPO.class,ArrayList.class,CountryListVO.class,
    LoginHistoryVO.class,SignupVO.class,RegistrationSSOVO.class,
    CityListVO.class,StateListVO.class,UserSearchVO.class,TokenVO.class,UserManagementVO.class,ModifystatusVO.class,UserDetailVO.class,
    DeleteUserVO.class,MasterDataTypeListVO.class,MasterDataTypeGetByIdVO.class,MasterDataTypeSearchVO.class,MasterDataListVO.class,
    MasterDataGetByIdVO.class,MasterDataSearchByIdVO.class,MasterDataAddVO.class,MasterDataUpdateVO.class,MasterDataDeleteVO.class,
    MasterDataMultipleDeleteVO.class,UserSessionVO.class,MergeUserVO.class,DeleteComponentVO.class, MasterDataSearchVO.class,
    NewsletterDeleteVO.class,NewsletterVOList.class,NewsletterSearchVO.class,UserCommentAddVO.class,UserCommentDeleteVO.class,
    UserCommentGetByIdVO.class,UserCommentListVO.class,UserCommentsUpdateVO.class,UserCommentMultiDeleteVO.class,EmailTemplateTypeListVO.class,
    EmailTemplateTypeGetByIdVO.class,EmailTemplateTypeSearchVO.class,EmailTemplateListVO.class,EmailTemplateGetByIdVO.class,EmailTemplateSearchVO.class,
    EmailTemplateAddVO.class,EmailTemplateUpdateVO.class,EmailTemplateBulkDeleteVO.class,EmailTemplateCloneVO.class,UserEmailLogListVO.class,
    UserEmailLogSearchVO.class,UserActivationVO.class,ResendActivationMailVO.class,AddressVO.class,EmailTemplateTypeSearchPO.class,EmailTemplateTypeSearch.class, 
    UserServiceModel.class,NewsletterSearchPO.class, UserPreferenceVO.class,CreditCardVO.class,ViewImporterVO.class,ViewWineryVO.class,WineryPO.class,WineryUpdatePO.class,
    WineryDeletePO.class,WineryBulkDeleteVO.class,WineryImporterAddressBasicVO.class,ViewWineVO.class,WineBasicSearchVO.class, WineSearchVO.class,ViewWineLogisticVO.class,
    WineLogisticBasicVO.class,WineVO.class,WineAdvanceSearchVO.class,WineSearchVO.class,WineDeleteVO.class,WineryLicenseDetailVO.class,WineryLicenseDetailViewVO.class,
    ViewWineryOwsVO.class,AddUpdateWineryOwsVO.class,WineLicenseDetailVO.class,WineLicenseDetailViewVO.class,WineryPermitAddVO.class,WineryPermitDetailVO.class,
    ViewWineOwsVO.class,AddRequisitionVO.class,UserAddressesListVO.class,UserCreditCardsListVO.class,WineryEnableDisableVO.class, WinerySearchVO.class, AddWarehouseVO.class, 
    WarehouseDetails.class,	AddDistributionCentreVO.class,UpdateDistributionCentreVO.class,ViewDistributionCentreVO.class,DistributionCentreListingVO.class,
    AddWineToRequisitionVO.class,ListWarehouseVO.class,ListWineInRequisitionVO.class,POSearchVO.class,AddSamplerVO.class,ViewRequisitionVO.class,ViewSamplerVO.class,
    RemoveWineFromRequisitionVO.class, EditSamplerWineVO.class,WinePermitDetailVO.class,UpdateSamplerVO.class,ViewSamplerLogisticsDetailVO.class,AddSamplerProductVO.class,
    SamplerSearchVO.class,SamplerAdvSearchVO.class,WinePermitAddVO.class,ImporterEnableDisableVO.class,ViewSamplerComplienceDetailVO.class,ImporterSearchVO.class,DeleteSamplerProductVO.class,
    CreateUserPasswordVO.class,SamplerProductDetails.class,ListSamplerProductVO.class,ProductDetail.class,DeleteImporterVO.class, EditRequisitionVO.class, RequisitionDetails.class, DeleteRequisitionVO.class})


public class SuccessResponse extends Response {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Object payload;

    public SuccessResponse() {
    }

    public SuccessResponse(Object payload, int status) {
	this.setStatus(status);
	this.payload = payload;
    }

    public Object getPayload() {
	return payload;
    }

    public void setPayload(Object payload) {
	this.payload = payload;
    }
}
