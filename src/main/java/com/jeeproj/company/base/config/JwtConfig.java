package com.jeeproj.company.base.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ResourceBundle;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtConfig {
    private static final ResourceBundle rb = ResourceBundle.getBundle("jwt");
    private static final Integer TIME_TO_LIVE = Integer.valueOf(rb.getString("jwt.time-to-live"));
    private static final String SECRET_KEY = rb.getString("jwt.secret.key");

    private static final String ISSUER = rb.getString("jwt.issuer");

    public static String getSecretKey() {
        return SECRET_KEY;
    }

    public static String getIssuer() {
        return ISSUER;
    }

    public static Integer getTimeToLive() {
        return TIME_TO_LIVE;
    }
}
