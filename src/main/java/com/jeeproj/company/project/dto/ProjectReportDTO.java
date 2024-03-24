package com.jeeproj.company.project.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.jeeproj.company.department.dto.DepartmentDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProjectReportDTO {
    private Long id;
    private String projectName;
    private String area;
    private String status;
    private DepartmentDTO department;
    private int numberOfEmployees;
    private int numberOfHours;
    private double totalSalary;
}
