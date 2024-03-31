package com.jeeproj.company.base.filter;

import com.jeeproj.company.base.security.payload.JwtPayload;
import lombok.AllArgsConstructor;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

@AllArgsConstructor
public class RequestSecurityContext implements SecurityContext {
    private JwtPayload payload;

    @Override
    public Principal getUserPrincipal() {
        return payload;
    }

    @Override
    public boolean isUserInRole(String s) {
        return payload.getRole().toString().trim().equals(s);
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public String getAuthenticationScheme() {
        return null;
    }
}
