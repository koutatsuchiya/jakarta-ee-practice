package com.jeeproj.company.assignment.dto;

import com.jeeproj.company.employee.dto.EmployeeResponseDTO;
import com.jeeproj.company.project.dto.ProjectDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentDTO {
    private Long id;
    private int numberOfHour;
    private EmployeeResponseDTO employee;
    private ProjectDTO project;
}
