package com.jeeproj.company.department.service;

import com.jeeproj.company.base.exception.BadRequestException;
import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.base.message.AppMessage;
import com.jeeproj.company.department.dto.DepartmentDTO;
import com.jeeproj.company.department.dto.DepartmentRequestDTO;
import com.jeeproj.company.department.dto.DepartmentRequestsDTO;
import com.jeeproj.company.department.entity.Department;
import com.jeeproj.company.department.dao.DepartmentDAO;
import com.jeeproj.company.department.service.cache.DepartmentCache;
import com.jeeproj.company.department.service.cache.DepartmentCacheConstant;
import com.jeeproj.company.department.service.mapper.DepartmentMapper;
import com.jeeproj.company.department_location.dao.DepartmentLocationDAO;
import com.jeeproj.company.employee.dao.EmployeeDAO;
import com.jeeproj.company.project.dao.ProjectDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Stateless
public class DepartmentService {
    @Inject
    DepartmentDAO departmentDAO;

    @Inject
    EmployeeDAO employeeDAO;

    @Inject
    ProjectDAO projectDAO;

    @Inject
    DepartmentLocationDAO departmentLocationDAO;

    @Inject
    DepartmentMapper departmentMapper;

    @Inject
    DepartmentCache departmentCache;

    public List<DepartmentDTO> getDepartments() {
        List<Department> departments = departmentCache.getCache()
                .getIfPresent(DepartmentCacheConstant.departmentsKey);
        if (departments == null) {
            departments = departmentDAO.findAll();
            departmentCache.getCache().put(DepartmentCacheConstant.departmentsKey, departments);
        }
        return departmentMapper.toDepartmentDTOs(departmentDAO.findAll());
    }

    public DepartmentDTO getDepartmentById(Long id) throws NotFoundException {
        Department department = departmentDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(AppMessage.DEPARTMENT_NOT_FOUND));
        return departmentMapper.toDepartmentDTO(department);
    }

    public DepartmentDTO add(DepartmentRequestDTO departmentRequestDTO) throws BadRequestException {
        checkDuplicatedDepartment(departmentRequestDTO.getName());
        return departmentMapper.toDepartmentDTO(departmentDAO.add(departmentMapper.toDepartment(departmentRequestDTO)));
    }

    public List<DepartmentDTO> addDepartments(DepartmentRequestsDTO departmentRequestsDTO) throws BadRequestException {
        List<String> deptNames = departmentRequestsDTO.getDepartments().stream()
                .map(DepartmentRequestDTO::getName).toList();
        checkDuplicatedDepartments(deptNames);
        List<Department> departments = departmentMapper.toDepartments(departmentRequestsDTO.getDepartments());
        departments.forEach(deptToAdd -> {
            departmentDAO.add(deptToAdd);
        });
        return departmentMapper.toDepartmentDTOs(departments);
    }

    public DepartmentDTO update(Long id, DepartmentRequestDTO departmentRequestDTO) throws NotFoundException {
        Department dept = departmentDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(AppMessage.DEPARTMENT_NOT_FOUND));
        departmentMapper.updateDepartment(dept, departmentRequestDTO);

        return departmentMapper.toDepartmentDTO(dept);
    }

    @Transactional(rollbackOn = {
            RuntimeException.class,
            NotFoundException.class
    })
    public void removeDepartment(Long id) throws NotFoundException {
        Department dept = departmentDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(AppMessage.DEPARTMENT_NOT_FOUND));
        employeeDAO.deleteEmployeesByDepartment(id);
        projectDAO.deleteProjectsByDepartment(id);
        departmentLocationDAO.deleteLocationsByDepartment(id);
        departmentDAO.delete(dept);
    }

    private void checkDuplicatedDepartment(String deptName) throws BadRequestException {
        Optional<Department> department = departmentDAO.findDepartmentByName(deptName);
        if (department.isPresent()) {
            throw new BadRequestException(AppMessage.DEPARTMENT_EXIST);
        }
    }

    private void checkDuplicatedDepartments(List<String> deptNames) throws BadRequestException {
        List<Department> departments = departmentDAO.findAll();
        Map<String, Department> departmentsMap = new HashMap<>();
        departments.forEach(department -> departmentsMap.put(department.getName(), department));

        if (deptNames.stream().anyMatch(deptName -> departmentsMap.get(deptName) != null)) {
            throw new BadRequestException(AppMessage.DEPARTMENTS_EXIST);
        }
    }
}
