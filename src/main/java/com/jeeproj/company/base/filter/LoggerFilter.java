package com.jeeproj.company.base.filter;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(1)
public class LoggerFilter implements ContainerRequestFilter {
    @Context
    HttpServletRequest httpRequest;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String ip = httpRequest.getRemoteAddr();
        String path = requestContext.getUriInfo().getAbsolutePath().getPath();
        String method = requestContext.getMethod();
        String message = String.format("%s:%s:%s", ip, method, path);

        System.out.println(message);
    }
}
