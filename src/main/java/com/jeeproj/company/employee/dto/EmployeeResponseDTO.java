package com.jeeproj.company.employee.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jeeproj.company.department.dto.DepartmentRequestDTO;
import com.jeeproj.company.base.enums.Gender;
import com.jeeproj.company.base.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EmployeeResponseDTO {
    private Long id;
    private String fullName;
    private Double salary;
    private String dateOfBirth;
    private Gender gender;
    private Status status;
    private DepartmentRequestDTO department;
}
