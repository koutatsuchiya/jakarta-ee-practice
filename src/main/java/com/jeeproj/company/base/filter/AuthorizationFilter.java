package com.jeeproj.company.base.filter;

import com.jeeproj.company.base.exception.UnauthorizedException;
import com.jeeproj.company.base.message.AppMessage;
import lombok.SneakyThrows;

import javax.annotation.Priority;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Arrays;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {
    @Context
    private ResourceInfo rsInfo;

    @Context
    private SecurityContext secCtx;

    @Override
    public void filter(ContainerRequestContext reqCtx) throws IOException {
        RolesAllowed rolesAllowedAnnotation = rsInfo.getResourceMethod().getAnnotation(RolesAllowed.class);
        if (rolesAllowedAnnotation != null) {
            if (rolesAllowedAnnotation.value().length > 0) {
                checkUserInRole(rolesAllowedAnnotation.value());
            }
        }
    }

    @SneakyThrows
    private void checkUserInRole(String[] roles) {
        boolean isForbidden = Arrays.stream(roles).noneMatch(role -> secCtx.isUserInRole(role));
        if (isForbidden) {
            throw new UnauthorizedException(AppMessage.PERMISSION_DENIED);
        }
    }
}
