package com.jeeproj.company.employee.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jeeproj.company.base.validations.ValueOfEnum;
import com.jeeproj.company.department.entity.Department;
import com.jeeproj.company.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EmployeeRequestDTO {
    @NotBlank(message = "Employee first name must not be blank")
    private String firstName;

    @NotBlank(message = "Employee last name must not be blank")
    private String lastName;

    private String middleName;
    private Integer salary;

    @NotNull(message = "Date of birth is required")
    @JsonbDateFormat("yyyy-MM-dd")
    private String dateOfBirth;

    @NotNull(message = "Gender is required")
    @ValueOfEnum(enumClass = Gender.class)
    private Gender gender;

    private Long departmentId;
}
