package com.jeeproj.company.relative.dto;

import com.jeeproj.company.base.enums.Gender;
import com.jeeproj.company.base.validations.ValueOfEnum;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelativeRequestDTO {
    @NotBlank(message = "Relative name must not be blank")
    private String fullName;

    @NotNull(message = "Gender is required")
    @ValueOfEnum(enumClass = Gender.class)
    private Gender gender;

    private String phoneNumber;
    private String relationship;
    private Long employeeId;
}
