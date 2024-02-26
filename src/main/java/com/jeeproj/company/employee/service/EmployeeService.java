package com.jeeproj.company.employee.service;

import com.jeeproj.company.assignment.dao.AssignmentDao;
import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.employee.dto.EmployeeRequestDTO;
import com.jeeproj.company.employee.dto.EmployeeResponseDTO;
import com.jeeproj.company.employee.entity.Employee;
import com.jeeproj.company.employee.dao.EmployeeDAO;
import com.jeeproj.company.department.dao.DepartmentDAO;
import com.jeeproj.company.employee.service.mapper.EmployeeMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@Stateless
public class EmployeeService {

    @Inject
    EmployeeDAO employeeDAO;

    @Inject
    DepartmentDAO departmentDAO;

    @Inject
    AssignmentDao assignmentDAO;

    @Inject
    EmployeeMapper employeeMapper;

    public List<Employee> getEmployees() {
        return employeeDAO.findAll();
    }

    public Employee getEmployeeById(Long id) throws NotFoundException {
        return employeeDAO.findById(id).orElseThrow(() -> new NotFoundException("Employee not found"));
    }

//    public List<Employee> get(Long dpt) throws EntityNotFoundException {
//        throw new EntityNotFoundException("Employee Not Found.");
//        throw new BadRequestException("Bad request");

//        return employeeDAO.getEmployeeByDepartmentID(dpt);
//    }

    public EmployeeResponseDTO createEmployee(@Valid EmployeeRequestDTO newEmp) {
        Employee addedEmp = employeeDAO.add(employeeMapper.toEmployee(newEmp));

        return employeeMapper.toEmployeeResponseDTO(addedEmp);
    }

    public Employee updateEmployee(Long id, @Valid EmployeeRequestDTO emp) throws NotFoundException {
        employeeDAO.findById(id).orElseThrow(() -> new NotFoundException("Employee not found"));

        return employeeDAO.update(employeeMapper.toEmployee(emp));
    }

    public void removeEmployee(Long id) {
        employeeDAO.delete(id);
    }
}
