package com.jeeproj.company.department.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jeeproj.company.base.enums.Status;
import com.jeeproj.company.base.validations.ValueOfEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DepartmentDTO {
    private Long id;

    @NotBlank(message = "department name must not be blank")
    private String departmentName;

    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate startDate;

    @ValueOfEnum(enumClass = Status.class)
    private Status status;
}
