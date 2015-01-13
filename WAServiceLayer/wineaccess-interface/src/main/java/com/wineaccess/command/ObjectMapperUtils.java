package com.wineaccess.command;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.xml.sax.InputSource;

import com.wineaccess.property.utils.PropertyholderUtils;
import com.wineaccess.response.ExceptionHandlerUtil;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.Response;
import com.wineaccess.response.ValidationFailedError;
import com.wineaccess.response.WineaccessError;

public class ObjectMapperUtils {
	
	static ObjectMapper objectMapper = new ObjectMapper();
	static ObjectMapper objectMapperWithDateFormat = new ObjectMapper();
	
	public static BasePO getObjectWithDateFormat(String acceptType, String postParameter, Class claz, String dateFormat) throws Exception, ValidationFailedError {
		
		try {
			BasePO basePO = null;
			
			if (acceptType.equals("application/json")) {
				objectMapperWithDateFormat.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
				objectMapperWithDateFormat.setDateFormat(new SimpleDateFormat(dateFormat));
				basePO =(BasePO) objectMapperWithDateFormat.readValue(postParameter, claz);
			} else {
				JAXBContext context = JAXBContext.newInstance(claz);
				Unmarshaller un = context.createUnmarshaller();
				basePO = (BasePO) un.unmarshal(new InputSource(new StringReader(postParameter)));
			}
			List<String> errors  = basePO.validate();
			if (!errors.isEmpty()) {
				Response failedResponse = new FailureResponse();
				failedResponse.setStatus(200);
				for (String error : errors) {
					WineaccessError wineError = new WineaccessError(error.split("\\n")[0], error.split("\\n")[1]);
					failedResponse.addError(wineError);
				}
				throw new ValidationFailedError(failedResponse);
			}
			return basePO;
		} catch(JsonMappingException jsonMappingException) {
			throw ExceptionHandlerUtil.analyzeJsonObjectException(jsonMappingException);
		} catch(JsonParseException jsonParseException) {
			throw ExceptionHandlerUtil.analyzeJsonObjectException(jsonParseException);
		}
	}
	
	public static BasePO getObject(String accept, String postParameter, Class claz) throws Exception, ValidationFailedError {
		BasePO basePO = null;
		try {
			if (accept.equals("application/json")) {
				objectMapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
				objectMapper.setDateFormat(new SimpleDateFormat(PropertyholderUtils.getStringProperty("response.date.format")));
				basePO =(BasePO) objectMapper.readValue(postParameter, claz);
			} else {
				JAXBContext context = JAXBContext.newInstance(claz);
				Unmarshaller un = context.createUnmarshaller();
				basePO = (BasePO) un.unmarshal(new InputSource(new StringReader(postParameter)));
			}
			List<String> errors  = basePO.validate();
			if (!errors.isEmpty()) {
				Response failedResponse = new FailureResponse();
				failedResponse.setStatus(200);
				for (String error : errors) {
					WineaccessError wineError = new WineaccessError(error.split("\\n")[0], error.split("\\n")[1]);
					failedResponse.addError(wineError);
				}
				throw new ValidationFailedError(failedResponse);
			}
			return basePO;
		} catch(JsonMappingException jsonMappingException) {
			throw ExceptionHandlerUtil.analyzeJsonObjectException(jsonMappingException);
		} catch(JsonParseException jsonParseException) {
			throw ExceptionHandlerUtil.analyzeJsonObjectException(jsonParseException);
		}
	}
}
