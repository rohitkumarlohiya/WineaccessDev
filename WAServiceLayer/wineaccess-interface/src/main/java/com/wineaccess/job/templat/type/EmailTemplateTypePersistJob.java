package com.wineaccess.job.templat.type;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.beans.factory.utils.CoreBeanFactory;
import com.wineaccess.data.model.common.EmailTemplate;
import com.wineaccess.data.model.common.EmailTemplateType;
import com.wineaccess.emailtemplate.EmailTemplatePO;
import com.wineaccess.job.master.data.MasterDataJob;
import com.wineaccess.persistence.dao.GenericDAO;
import com.wineaccess.property.utils.PropertyConstants;
import com.wineaccess.property.utils.PropertyholderUtils;

public class EmailTemplateTypePersistJob implements Runnable {
	
private static Log logger = LogFactory.getLog(MasterDataJob.class);
	
	public static String EMAIL_TEMPLAT_TYPE_FILE = System.getenv("WINEACCESS_HOME") + "/emailTemplatType.xml";
	private long lastModifiedTime = 0L;
	
	
	@Override
	public void run() {
		
		do {
			try {
				File f = new File(EMAIL_TEMPLAT_TYPE_FILE);
				long lastModified = f.lastModified();
				
				if (lastModified > lastModifiedTime) {
					JAXBContext context = JAXBContext.newInstance(EmailTemplateTypeJobPO.class);
					Unmarshaller un = context.createUnmarshaller();
					EmailTemplateTypeJobPO emailTemplateTypeJobPO = (EmailTemplateTypeJobPO) un.unmarshal(f);
					
					for (EmailTemplateTypeJobModel emailTemplateTypeJobModel : emailTemplateTypeJobPO.getAddUpdateEmailTemplateType()) {

						if (emailTemplateTypeJobModel.getName().isEmpty()) {
							logger.error("Invalid Data -> Master Data Name is Blank");
							continue;
						}

						if (!(emailTemplateTypeJobModel.getStatus().equals("true") || emailTemplateTypeJobModel.getStatus().equals("false"))) {
							logger.error("Invalid Data -> status could be either true or false");
							continue;
						}

						boolean status = Boolean.parseBoolean(emailTemplateTypeJobModel.getStatus());
						EmailTemplateType emailTemplateType = new EmailTemplateType(emailTemplateTypeJobModel.getName(),emailTemplateTypeJobModel.getDescription(),
								status, emailTemplateTypeJobModel.getLabel());

						GenericDAO<EmailTemplateType> emailTemplateDAO = (GenericDAO<EmailTemplateType>) CoreBeanFactory.getBean("genericDAO");
						List<EmailTemplateType> emailTemplates = emailTemplateDAO.findByNamedQuery("EmailTemplateType.getAllByName",new String[] { "name" },
										emailTemplateType.getName());

						if (!emailTemplates.isEmpty()) {
							emailTemplates.get(0).setDescription(emailTemplateType.getDescription());
							emailTemplates.get(0).setStatus(emailTemplateType.isStatus());
							emailTemplates.get(0).setLabel(emailTemplateType.getLabel());
							emailTemplateDAO.update(emailTemplates.get(0));
						} else {
							emailTemplateDAO.create(emailTemplateType);
						}

						for (EmailTemplatePO emailTemplatePO : emailTemplateTypeJobModel.getAddUpdateEmailTemplate()) {
							if (emailTemplatePO.getName().isEmpty()) {
								logger.error("Invalid Data -> Email Template Master Data Name is Blank");
								continue;
							}
							String fromEmail = StringUtils.EMPTY;
							if (StringUtils.isNotBlank(PropertyholderUtils.getStringProperty(PropertyConstants.SUPPORT_EMAIL))) {
								fromEmail = PropertyholderUtils.getStringProperty(PropertyConstants.SUPPORT_EMAIL);
							} else {
								fromEmail = "support@wineaccess.com";
							}
							EmailTemplate emailTemplate = new EmailTemplate(emailTemplatePO.getName(),emailTemplatePO.getSubject(),emailTemplatePO.getIsDefault(),
									emailTemplatePO.getBody(), fromEmail,emailTemplates.get(0));
							GenericDAO<EmailTemplate> templateDao = (GenericDAO<EmailTemplate>) CoreBeanFactory.getBean("genericDAO");
							List<EmailTemplate> emailTemplateList = templateDao.findByNamedQuery("EmailTemplate.getAllByName",new String[] { "name" },emailTemplate.getName());
							if (!emailTemplateList.isEmpty()) {
								emailTemplateList.get(0).setName(emailTemplate.getName());
								emailTemplateList.get(0).setBody(emailTemplate.getBody());
								emailTemplateList.get(0).setFromEmail(emailTemplate.getFromEmail());
								emailTemplateList.get(0).setIsActive(emailTemplate.isIsActive());
								emailTemplateList.get(0).setSubject(emailTemplate.getSubject());
								emailTemplateList.get(0).setEmailTemplateType(emailTemplate.getEmailTemplateType());
								templateDao.update(emailTemplateList.get(0));
							} else {
								templateDao.create(emailTemplate);
							}

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
