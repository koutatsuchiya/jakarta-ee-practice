package com.jeeproj.company.base.exception;

import com.jeeproj.company.base.exception.body.ExceptionBody;
import lombok.Getter;

import javax.ejb.ApplicationException;

@Getter
@ApplicationException
public abstract class AppException extends Exception {
    private final ExceptionBody body;

    protected AppException(int statusCode, String errorKey, String message) {
        super(message);
        this.body = new ExceptionBody(false, statusCode, errorKey, message, null);
    }
}
