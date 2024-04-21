package com.jeeproj.company.departmentlocation.service.mapper;

import com.jeeproj.company.departmentlocation.dto.DepartmentLocationDTO;
import com.jeeproj.company.departmentlocation.dto.DepartmentLocationRequestDTO;
import com.jeeproj.company.departmentlocation.entity.DepartmentLocation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface DepartmentLocationMapper {
    DepartmentLocation toDepartmentLocation(DepartmentLocationRequestDTO departmentLocationRequestDTO);

    DepartmentLocationDTO toDepartmentLocationDTO(DepartmentLocation departmentLocation);

    List<DepartmentLocationDTO> toDepartmentLocationDTOs(List<DepartmentLocation> departmentLocations);

    void updateDepartmentLocation(@MappingTarget DepartmentLocation departmentLocation,
                                  DepartmentLocationRequestDTO departmentLocationRequestDTO);
}
