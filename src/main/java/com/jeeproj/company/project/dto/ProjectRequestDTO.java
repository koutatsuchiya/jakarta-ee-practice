package com.jeeproj.company.project.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jeeproj.company.base.enums.Status;
import com.jeeproj.company.base.validations.ValueOfEnum;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProjectRequestDTO {
    @NotBlank(message = "Project name must not be blank")
    private String projectName;

    @NotBlank(message = "Project area must not be blank")
    private String area;

    @ValueOfEnum(enumClass = Status.class)
    private Status status;

    private Long departmentId;
}
