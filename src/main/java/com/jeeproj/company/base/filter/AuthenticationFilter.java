package com.jeeproj.company.base.filter;

import com.jeeproj.company.base.exception.UnauthorizedException;
import com.jeeproj.company.base.exception.body.ExceptionBody;
import com.jeeproj.company.base.message.AppMessage;
import com.jeeproj.company.base.security.payload.JwtPayload;
import com.jeeproj.company.base.security.tokenprovider.TokenProvider;
import lombok.SneakyThrows;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Secure
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    private final String AUTHORIZATION_HEADER = "Authorization";

    @Inject
    private TokenProvider tokenProvider;

    @Override
    public void filter(ContainerRequestContext reqCtx) throws IOException {
        String token = getTokenFromHeader(reqCtx);

        JwtPayload payload = getPayloadFromToken(token);

        // Add payload to request context
        reqCtx.setSecurityContext(new RequestSecurityContext(payload));
    }

    @SneakyThrows
    private JwtPayload getPayloadFromToken(String token) {
        try {
            return JwtPayload.fromMap(tokenProvider.validateToken(token));
        } catch (UnauthorizedException e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }

    private String getTokenFromHeader(ContainerRequestContext reqCtx) {
        String authHeader = reqCtx.getHeaderString(AUTHORIZATION_HEADER);

        if (authHeader == null ||
            !authHeader.startsWith("Bearer ") ||
            authHeader.split(" ")[1].trim().isEmpty()
        ) {
            ExceptionBody body = new ExceptionBody(
                    false,
                    Response.Status.FORBIDDEN.getStatusCode(),
                    Response.Status.FORBIDDEN.getReasonPhrase(),
                    AppMessage.PERMISSION_DENIED,
                    null
            );
            reqCtx.abortWith(
                Response.status(body.getStatusCode())
                        .type(MediaType.APPLICATION_JSON)
                        .entity(body)
                        .build()
            );
            return null;
        }

        return authHeader.split(" ")[1].trim();
    }
}
