package com.wineaccess.util.notification;

import javax.mail.PasswordAuthentication;

import com.wineaccess.crypto.util.CryptoUtil;
import com.wineaccess.property.utils.WebServicesPropertyHolderUtils;

public class SMTPAuthenticator extends javax.mail.Authenticator {

	public PasswordAuthentication getPasswordAuthentication() {

		try {
			String username = CryptoUtil.decrypt(WebServicesPropertyHolderUtils
					.getStringProperty("sendgrid.account.username"));
			String password = CryptoUtil.decrypt(WebServicesPropertyHolderUtils
					.getStringProperty("sendgrid.account.password"));

			return new PasswordAuthentication(username, password);
		} catch (Exception e) {
		}
		return null;
	}

}
