package com.jeeproj.company.base.filter;

import com.jeeproj.company.base.exception.UnauthorizedException;
import com.jeeproj.company.base.message.AppMessage;
import lombok.SneakyThrows;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Secure
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {
    @Context
    private ResourceInfo rsInfo;

    @Context
    private SecurityContext secCtx;

    @Override
    public void filter(ContainerRequestContext reqCtx) throws IOException {
        Secure secureAnnotation = rsInfo.getResourceMethod().getAnnotation(Secure.class);
        if (secureAnnotation != null) {
            if (!secureAnnotation.role().isEmpty()) {
                checkUserInRole(secureAnnotation.role());
            }
        }
    }

    @SneakyThrows
    private void checkUserInRole(String role) {
        if (!secCtx.isUserInRole(role)) {
            throw new UnauthorizedException(AppMessage.PERMISSION_DENIED);
        }
    }
}
