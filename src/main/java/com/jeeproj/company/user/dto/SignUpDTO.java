package com.jeeproj.company.user.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jeeproj.company.base.validations.ValueOfEnum;
import com.jeeproj.company.user.entity.Role;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SignUpDTO {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    private String displayName;

    @NotBlank(message = "Password is required")
    private String password;

    @ValueOfEnum(enumClass = Role.class)
    @NotNull(message = "Role is required")
    private Role role;
}
