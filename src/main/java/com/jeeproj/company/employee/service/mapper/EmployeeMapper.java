package com.jeeproj.company.employee.service.mapper;

import com.jeeproj.company.employee.dto.EmployeeRequestDTO;
import com.jeeproj.company.employee.dto.EmployeeResponseDTO;
import com.jeeproj.company.employee.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface EmployeeMapper {
    EmployeeResponseDTO toEmployeeResponseDTO(Employee employee);
    Employee toEmployee(EmployeeRequestDTO employeeRequestDTO);
}
