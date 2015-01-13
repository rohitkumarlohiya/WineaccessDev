package com.wineaccess.validator;

import com.wineaccess.property.utils.ErrorPropertyHolderUtil;

public class ValidationMessageInterpolator extends org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator {

	@Override
	public String interpolate(String message, Context context) {
		String resolvedMessage = super.interpolate(message, context);
		String validationError = message+"\n";
		validationError += ErrorPropertyHolderUtil.getProperty(resolvedMessage, resolvedMessage);
		return validationError;
	}
}
