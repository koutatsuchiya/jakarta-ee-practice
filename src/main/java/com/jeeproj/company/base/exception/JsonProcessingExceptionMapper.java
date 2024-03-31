package com.jeeproj.company.base.exception;

import com.jeeproj.company.base.exception.body.ExceptionBody;
import com.jeeproj.company.base.message.AppMessage;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonProcessingExceptionMapper implements ExceptionMapper<JsonProcessingException> {
    @Override
    public Response toResponse(JsonProcessingException e) {
        //e.printStackTrace();
        String message = e.getOriginalMessage().substring(e.getOriginalMessage().indexOf(':') + 2);
        ExceptionBody body = new ExceptionBody(
                false,
                Response.Status.BAD_REQUEST.getStatusCode(),
                Response.Status.BAD_REQUEST.getReasonPhrase(),
                message,
                null
        );
        return Response
                .status(body.getStatusCode())
                .entity(body)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
