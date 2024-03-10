package com.jeeproj.company.employee.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jeeproj.company.department.dto.DepartmentDTO;
import com.jeeproj.company.department.entity.Department;
import com.jeeproj.company.enums.Gender;
import com.jeeproj.company.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EmployeeResponseDTO {
    private Long id;
    private String fullName;
    private Integer salary;
    private String dateOfBirth;
    private Gender gender;
    private Status status;
    private DepartmentDTO department;
}
