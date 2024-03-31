package com.jeeproj.company.user.entity;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ADMIN"),
    USER("USER");

    private final String value;

    Role(String text) {
        this.value = text;
    }

    public static Role fromString(String text) {
        for (Role r : Role.values()) {
            if (r.value.equalsIgnoreCase(text)) {
                return r;
            }
        }
        return null;
    }
}
