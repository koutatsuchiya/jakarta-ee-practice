package com.jeeproj.company.department_location.service;

import com.jeeproj.company.base.exception.BadRequestException;
import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.base.exception.message.ExceptionMessage;
import com.jeeproj.company.department.dao.DepartmentDAO;
import com.jeeproj.company.department.entity.Department;
import com.jeeproj.company.department.service.mapper.DepartmentMapper;
import com.jeeproj.company.department_location.dao.DepartmentLocationDAO;
import com.jeeproj.company.department_location.dto.DepartmentLocationDTO;
import com.jeeproj.company.department_location.dto.DepartmentLocationRequestDTO;
import com.jeeproj.company.department_location.entity.DepartmentLocation;
import com.jeeproj.company.department_location.service.mapper.DepartmentLocationMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Stateless
public class DepartmentLocationService {
    @Inject
    private DepartmentLocationDAO departmentLocationDAO;

    @Inject
    private DepartmentDAO departmentDAO;

    @Inject
    private DepartmentLocationMapper departmentLocationMapper;

    @Inject
    private DepartmentMapper departmentMapper;

    public List<DepartmentLocationDTO> getAll() {
        return departmentLocationMapper.toDepartmentLocationDTOs(departmentLocationDAO.findAll());
    }

    public DepartmentLocationDTO getLocationById(Long id) throws NotFoundException {
        DepartmentLocation departmentLocation = departmentLocationDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.LOCATION_NOT_FOUND));
        return departmentLocationMapper.toDepartmentLocationDTO(departmentLocation);
    }

    public List<DepartmentLocationDTO> findLocationsByDepartmentId(Long departmentId) throws NotFoundException {
        Department department = departmentDAO.findById(departmentId)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.DEPARTMENT_NOT_FOUND));

        List<DepartmentLocation> departmentLocations = departmentLocationDAO
                .findLocationsByDepartmentId(department.getId());

        return departmentLocationMapper.toDepartmentLocationDTOs(departmentLocations);
    }

    public DepartmentLocationDTO add(DepartmentLocationRequestDTO departmentLocationRequestDTO)
            throws NotFoundException, BadRequestException {
        Department department = departmentDAO.findById(departmentLocationRequestDTO.getDepartmentId())
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.DEPARTMENT_NOT_FOUND));
        Optional<DepartmentLocation> existedDepartmentLocation = departmentLocationDAO.findDepartmentLocation(
                        departmentLocationRequestDTO.getLocation().trim().toLowerCase(),
                        departmentLocationRequestDTO.getDepartmentId());
        if(existedDepartmentLocation.isPresent()) {
            throw new BadRequestException(ExceptionMessage.LOCATION_EXIST);
        }

        DepartmentLocation newDepartmentLocation = departmentLocationMapper
                .toDepartmentLocation(departmentLocationRequestDTO);
        newDepartmentLocation.setDepartment(department);

        return departmentLocationMapper.toDepartmentLocationDTO(departmentLocationDAO.add(newDepartmentLocation));
    }

    public DepartmentLocationDTO update(Long id, DepartmentLocationRequestDTO departmentLocationRequestDTO)
            throws NotFoundException, BadRequestException {
        DepartmentLocation departmentLocation = departmentLocationDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.LOCATION_NOT_FOUND));
        Department department = departmentDAO.findById(departmentLocationRequestDTO.getDepartmentId())
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.DEPARTMENT_NOT_FOUND));
        Optional<DepartmentLocation> existedDepartmentLocation = departmentLocationDAO.findDepartmentLocation(
                        departmentLocationRequestDTO.getLocation().trim().toLowerCase(),
                        departmentLocationRequestDTO.getDepartmentId());
        if(existedDepartmentLocation.isPresent() &&
                !Objects.equals(existedDepartmentLocation.get().getId(), departmentLocation.getId())) {
            throw new BadRequestException(ExceptionMessage.LOCATION_EXIST);
        }

        departmentLocationMapper.updateDepartmentLocation(departmentLocation, departmentLocationRequestDTO);
        departmentLocation.setDepartment(department);

        return departmentLocationMapper.toDepartmentLocationDTO(departmentLocation);
    }

    public void delete(Long id) throws NotFoundException {
        DepartmentLocation departmentLocation = departmentLocationDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.DEPARTMENT_NOT_FOUND));
        departmentLocationDAO.delete(departmentLocation);
    }
}
