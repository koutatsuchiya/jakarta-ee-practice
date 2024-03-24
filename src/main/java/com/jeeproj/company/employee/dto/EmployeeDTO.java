package com.jeeproj.company.employee.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jeeproj.company.base.validations.ValueOfEnum;
import com.jeeproj.company.base.enums.Gender;
import com.jeeproj.company.base.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EmployeeDTO {
    @NotBlank(message = "Employee first name must not be blank")
    private String firstName;

    @NotBlank(message = "Employee last name must not be blank")
    private String lastName;

    private String middleName;
    private Double salary;

    @NotNull(message = "Date of birth is required")
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotNull(message = "Gender is required")
    @ValueOfEnum(enumClass = Gender.class)
    private Gender gender;

    @ValueOfEnum(enumClass = Status.class)
    private Status status;

    private Long departmentId;
}
