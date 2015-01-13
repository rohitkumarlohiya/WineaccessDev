package com.wineaccess.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wineaccess.application.validation.annotation.MasterDataAnnotationProcessor;
import com.wineaccess.application.validation.annotation.ValidatingListAnnotation;
import com.wineaccess.application.validation.annotation.ValidatingListAnnotationProcessor;
import com.wineaccess.sampler.SamplerAdapterHelper;
import com.wineaccess.validator.ValidationMessageInterpolator;

/**
 * Base Class for all the Parameter Objects
 */
public class BasePO {
	
	private static Validator validator = null;
	private static Log logger = LogFactory.getLog(BasePO.class);
	public List<String> validate() {
		List<String> errorMessages = new ArrayList<String>();
		try{
			if (validator == null) {
				Configuration<?> configuration = Validation.byDefaultProvider().configure();
		        ValidatorFactory validatorFactory = configuration.messageInterpolator(new ValidationMessageInterpolator()).buildValidatorFactory();
		        validator = validatorFactory.getValidator();
			}
			
			

			Set<ConstraintViolation<BasePO>> violations = validator.validate(this);
			
			errorMessages.addAll(MasterDataAnnotationProcessor.validate(this));
			errorMessages.addAll(ValidatingListAnnotationProcessor.validate(this));
			
			if (!violations.isEmpty()) {
				for (ConstraintViolation<BasePO> violation : violations) {
					errorMessages.add(violation.getMessage());
				}
			}	
		} catch(Exception e){
			logger.error("Error while validating fields. ", e);
		}
		
		return errorMessages;
	}
	
	
}
