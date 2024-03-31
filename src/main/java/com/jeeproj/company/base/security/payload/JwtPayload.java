package com.jeeproj.company.base.security.payload;

import com.jeeproj.company.user.entity.Role;
import lombok.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtPayload implements Principal {
    private static final String EMAIL_KEY = "email";
    private static final String ROLE_KEY = "role";

    private String email;
    private Role role;

    public Map<String, String> toMap() {
        Map<String, String> result = new HashMap<>();
        result.put(EMAIL_KEY, email);
        result.put(ROLE_KEY, role.toString());

        return result;
    }

    public static JwtPayload fromMap(Map<String, String> map) {
        JwtPayload payload = new JwtPayload();
        payload.setEmail(map.get(JwtPayload.EMAIL_KEY));
        payload.setRole(Role.fromString(map.get(JwtPayload.ROLE_KEY)));

        return payload;
    }

    @Override
    public String getName() {
        return email;
    }
}
