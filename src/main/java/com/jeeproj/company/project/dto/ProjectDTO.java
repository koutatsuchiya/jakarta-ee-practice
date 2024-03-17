package com.jeeproj.company.project.dto;

import com.jeeproj.company.department.entity.Department;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTO {
    private Long id;
    private String projectName;
    private String area;
    private Department department;
    private int numberOfEmployees;
    private int numberOfHours;
    private double totalSalary;
}
