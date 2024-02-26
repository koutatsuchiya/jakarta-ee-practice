package com.jeeproj.company.base.exception;

import lombok.Getter;

import javax.ejb.ApplicationException;
import javax.ws.rs.core.Response;

@Getter
@ApplicationException
public class NotFoundException extends AppException {

    public NotFoundException(String message) {
        super(Response.Status.NOT_FOUND.getStatusCode(),
                Response.Status.NOT_FOUND.getReasonPhrase(), message);
    }
}
