package com.jeeproj.company.department.service;

import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.department.dto.DepartmentDTO;
import com.jeeproj.company.department.entity.Department;
import com.jeeproj.company.department.dao.DepartmentDAO;
import com.jeeproj.company.department.service.mapper.DepartmentMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Stateless
public class DepartmentService {
    @Inject
    DepartmentDAO departmentDAO;

    @Inject
    DepartmentMapper departmentMapper;

    public List<Department> getDepartments() {
        return departmentDAO.findAll();
    }

    public DepartmentDTO getDepartmentById(Long id) throws NotFoundException {
        Department department = departmentDAO.findById(id).orElseThrow(() -> new NotFoundException("department not found"));
        return departmentMapper.toDepartmentDTO(department);
    }

    public DepartmentDTO createDepartment(@Valid DepartmentDTO newDept) {
        return departmentMapper.toDepartmentDTO(departmentDAO.add(departmentMapper.toDepartment(newDept)));
    }

    public DepartmentDTO updateDepartment(Long id, @Valid DepartmentDTO dept) throws NotFoundException {
        departmentDAO.findById(id).orElseThrow(() -> new NotFoundException("department not found"));
        Department updatedDept = departmentMapper.toDepartment(dept);
        updatedDept.setId(id);
        return departmentMapper.toDepartmentDTO(departmentDAO.update(updatedDept));
    }

    public void removeDepartment(Long id) {
        departmentDAO.delete(id);
    }

    public Optional<Department> getDeparmentById (Long id) {
        return this.departmentDAO.findById(id);
    }
}
