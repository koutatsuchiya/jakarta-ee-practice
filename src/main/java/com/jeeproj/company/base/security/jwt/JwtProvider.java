package com.jeeproj.company.base.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jeeproj.company.base.config.JwtConfig;
import com.jeeproj.company.base.exception.UnauthorizedException;
import com.jeeproj.company.base.message.AppMessage;
import com.jeeproj.company.base.security.tokenprovider.TokenProvider;

import javax.enterprise.context.Dependent;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Dependent
public class JwtProvider implements TokenProvider {
    private final Algorithm algorithm = Algorithm.HMAC256(JwtConfig.getSecretKey());

    @Override
    public String generateToken(Map<String, String> payload) {
        String token;
        try {
            // Create the JWT
            JWTCreator.Builder jwtBuilder = JWT.create();
            jwtBuilder.withPayload(payload);
            jwtBuilder.withIssuer(JwtConfig.getIssuer());
            jwtBuilder.withExpiresAt(new Date(System.currentTimeMillis() + JwtConfig.getTimeToLive())); // 3 hours

            token = jwtBuilder.sign(algorithm);
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new ServerErrorException(AppMessage.CREATE_JWT_FAILED,
                    Response.Status.INTERNAL_SERVER_ERROR, exception);
        }
        return token;
    }

    @Override
    public Map<String, String> validateToken(String token) throws UnauthorizedException {
        DecodedJWT decodedJWT;
        try {
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(JwtConfig.getIssuer()).build();
            decodedJWT = verifier.verify(token);
        } catch (JWTVerificationException exception){
            // Invalid signature/claims
            throw new UnauthorizedException(AppMessage.INVALID_TOKEN);
        }

        Map<String, String> result = new HashMap<>();
        for (var key : decodedJWT.getClaims().keySet()) {
            result.put(key, decodedJWT.getClaim(key).asString());
        }
        return result;
    }
}
