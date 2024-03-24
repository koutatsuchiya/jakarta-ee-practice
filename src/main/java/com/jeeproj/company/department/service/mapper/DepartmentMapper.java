package com.jeeproj.company.department.service.mapper;

import com.jeeproj.company.department.dto.DepartmentDTO;
import com.jeeproj.company.department.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface DepartmentMapper {
    DepartmentDTO toDepartmentDTO(Department department);

    Department toDepartment(DepartmentDTO departmentDTO);

    List<DepartmentDTO> toDepartmentDTOs(List<Department> departments);

    void updateDepartment(@MappingTarget Department department, DepartmentDTO departmentDTO);
}
