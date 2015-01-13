package com.wineaccess.restapi.exception.handler;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.api.NotFoundException;
import com.sun.jersey.api.ParamException;
import com.wineaccess.constants.WineaccessErrorCodes.SystemErrorCode;
import com.wineaccess.response.FailureResponse;
import com.wineaccess.response.WineaccessError;

@Provider
public class WAExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Context
    private HttpHeaders headers;

    @Override
    public Response toResponse(WebApplicationException exception){

	int status = exception.getResponse().getStatus();
	FailureResponse failedResponse = new FailureResponse();
	failedResponse.setStatus(200);

	if (status == 404 && exception instanceof NotFoundException) {
	    WineaccessError wineError = new WineaccessError(SystemErrorCode.BAD_URI, SystemErrorCode.BAD_URI_TEXT);
	    failedResponse.addError(wineError);
	} else if (status == 404 && exception instanceof ParamException) {
	    WineaccessError wineError = new WineaccessError(SystemErrorCode.INVALID_PARAMS, SystemErrorCode.INVALID_PARAMS_TEXT);
	    failedResponse.addError(wineError);
	} else if (status == 405) {
	    WineaccessError wineError = new WineaccessError(SystemErrorCode.METHOD_NOT_ALLOWED, SystemErrorCode.METHOD_NOT_ALLOWED_TEXT);
	    failedResponse.addError(wineError);
	} else {
	    WineaccessError wineError = new WineaccessError(SystemErrorCode.SYSTEM_ERROR, SystemErrorCode.SYSTEM_ERROR_TEXT);
	    failedResponse.addError(wineError);
	}
	return Response.status(Response.Status.OK).entity(failedResponse).type(getAcceptType()).build();
    }

    private String getAcceptType(){
	List<MediaType> accepts = headers.getAcceptableMediaTypes();
	if (accepts!=null && accepts.size() > 0) {
	    if (accepts.get(0).getSubtype().equals("xml")) {
		return "application/xml";
	    } else {
		return "application/json";
	    }
	}else {
	    return "application/json";
	}
    }
}
