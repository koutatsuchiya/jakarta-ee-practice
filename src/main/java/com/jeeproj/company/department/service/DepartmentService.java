package com.jeeproj.company.department.service;

import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.base.exception.message.ExceptionMessage;
import com.jeeproj.company.department.dto.DepartmentDTO;
import com.jeeproj.company.department.entity.Department;
import com.jeeproj.company.department.dao.DepartmentDAO;
import com.jeeproj.company.department.service.mapper.DepartmentMapper;
import com.jeeproj.company.department_location.dao.DepartmentLocationDAO;
import com.jeeproj.company.employee.dao.EmployeeDAO;
import com.jeeproj.company.project.dao.ProjectDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

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

    public List<DepartmentDTO> getDepartments() {
        return departmentMapper.toDepartmentDTOs(departmentDAO.findAll());
    }

    public DepartmentDTO getDepartmentById(Long id) throws NotFoundException {
        Department department = departmentDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.DEPARTMENT_NOT_FOUND));

        return departmentMapper.toDepartmentDTO(department);
    }

    public DepartmentDTO add(DepartmentDTO newDept) {
        return departmentMapper.toDepartmentDTO(departmentDAO.add(departmentMapper.toDepartment(newDept)));
    }

    public DepartmentDTO update(Long id, DepartmentDTO departmentDTO) throws NotFoundException {
        Department dept = departmentDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.DEPARTMENT_NOT_FOUND));
        departmentDTO.setId(dept.getId());
        departmentMapper.updateDepartment(dept, departmentDTO);

        return departmentMapper.toDepartmentDTO(dept);
    }

    @Transactional(rollbackOn = {
            RuntimeException.class,
            NotFoundException.class
    })
    public void removeDepartment(Long id) throws NotFoundException {
        Department dept = departmentDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.DEPARTMENT_NOT_FOUND));
        employeeDAO.deleteEmployeesByDepartment(id);
        projectDAO.deleteProjectsByDepartment(id);
        departmentLocationDAO.deleteLocationsByDepartment(id);
        departmentDAO.delete(dept);
    }
}
