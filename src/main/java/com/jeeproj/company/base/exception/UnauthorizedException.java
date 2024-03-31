package com.jeeproj.company.base.exception;

import lombok.Getter;

import javax.ejb.ApplicationException;
import javax.ws.rs.core.Response;

@Getter
@ApplicationException
public class UnauthorizedException extends AppException {
    public UnauthorizedException(String message) {
            super(Response.Status.UNAUTHORIZED.getStatusCode(),
                    Response.Status.UNAUTHORIZED.getReasonPhrase(), message);
    }
}
