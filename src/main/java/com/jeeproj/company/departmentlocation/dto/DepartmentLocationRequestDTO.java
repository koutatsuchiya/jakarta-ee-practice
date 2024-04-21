package com.jeeproj.company.departmentlocation.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentLocationRequestDTO {
    @NotBlank(message = "Location must not be blank")
    private String location;

    @NotNull(message = "Department's id is required")
    private Long departmentId;
}
