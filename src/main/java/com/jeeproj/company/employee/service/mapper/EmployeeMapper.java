package com.jeeproj.company.employee.service.mapper;

import com.jeeproj.company.employee.dto.EmployeeDTO;
import com.jeeproj.company.employee.dto.EmployeeResponseDTO;
import com.jeeproj.company.employee.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface EmployeeMapper {
    EmployeeResponseDTO toEmployeeResponseDTO(Employee employee);

    Employee toEmployee(EmployeeDTO employeeDTO);

    List<EmployeeResponseDTO> toEmployeeResponseDTOs(List<Employee> employees);

    void updateEmployee(@MappingTarget Employee employee, EmployeeDTO employeeDTO);
}
