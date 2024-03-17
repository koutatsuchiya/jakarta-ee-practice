package com.jeeproj.company.assignment.dto;

import com.jeeproj.company.employee.entity.Employee;
import com.jeeproj.company.project.entity.Project;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentDTO {
    private Long id;
    private int numberOfHour;
    private Employee employee;
    private Project project;
}
