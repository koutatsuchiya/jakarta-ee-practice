package com.jeeproj.company.base.exception;

import com.jeeproj.company.base.exception.body.ExceptionBody;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InternalExceptionMapper implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException e) {
        ExceptionBody body = new ExceptionBody(
                false,
                Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                null
        );
        return Response
                .status(body.getStatusCode())
                .entity(body)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
