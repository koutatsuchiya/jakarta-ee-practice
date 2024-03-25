package com.jeeproj.company.department_location.dto;

import com.jeeproj.company.department.dto.DepartmentDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentLocationDTO {
    private Long id;
    private String location;
    private DepartmentDTO department;
}
