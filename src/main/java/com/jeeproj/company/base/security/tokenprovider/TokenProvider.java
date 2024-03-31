package com.jeeproj.company.base.security.tokenprovider;

import com.jeeproj.company.base.exception.UnauthorizedException;

import java.util.Map;

public interface TokenProvider {
    String generateToken(Map<String, String> payload);
    Map<String, String> validateToken(String token) throws UnauthorizedException;
}
