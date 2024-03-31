package com.jeeproj.company.base.exception;

import com.jeeproj.company.base.exception.body.ExceptionBody;

import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {
    @Override
    public Response toResponse(ValidationException e) {
        String[] messages = e.getMessage().split(",");
        for (int i = 0; i < messages.length; i++) {
            messages[i] = messages[i].split(": ")[1];
        }
        ExceptionBody body = new ExceptionBody(
                false,
                Response.Status.BAD_REQUEST.getStatusCode(),
                Response.Status.BAD_REQUEST.getReasonPhrase(),
                null,
                messages
        );
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(body)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
