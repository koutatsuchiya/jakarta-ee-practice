package com.jeeproj.company.employee.service;

import com.jeeproj.company.assignment.dao.AssignmentDAO;
import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.base.message.AppMessage;
import com.jeeproj.company.department.entity.Department;
import com.jeeproj.company.employee.dto.EmployeeDTO;
import com.jeeproj.company.employee.dto.EmployeeResponseDTO;
import com.jeeproj.company.employee.entity.Employee;
import com.jeeproj.company.employee.dao.EmployeeDAO;
import com.jeeproj.company.department.dao.DepartmentDAO;
import com.jeeproj.company.employee.service.mapper.EmployeeMapper;
import com.jeeproj.company.relative.dao.RelativeDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class EmployeeService {
    @Inject
    EmployeeDAO employeeDAO;

    @Inject
    DepartmentDAO departmentDAO;

    @Inject
    RelativeDAO relativeDAO;

    @Inject
    AssignmentDAO assignmentDAO;

    @Inject
    EmployeeMapper employeeMapper;

    public List<EmployeeResponseDTO> getEmployees() {
        return employeeMapper.toEmployeeResponseDTOs(employeeDAO.findAll());
    }

    public EmployeeResponseDTO getEmployeeById(Long id) throws NotFoundException {
        Employee emp = employeeDAO.findById(id).orElseThrow(() -> new NotFoundException(AppMessage.EMPLOYEE_NOT_FOUND));

        return employeeMapper.toEmployeeResponseDTO(emp);
    }

    public List<EmployeeResponseDTO> getEmployeesByDepartment(Long deptId) throws NotFoundException {
        departmentDAO.findById(deptId).orElseThrow(() -> new NotFoundException(AppMessage.DEPARTMENT_NOT_FOUND));

        return employeeMapper.toEmployeeResponseDTOs(employeeDAO.getEmployeeByDepartmentID(deptId));
    }

    public EmployeeResponseDTO add(EmployeeDTO newEmployeeDTO) throws NotFoundException {
        Employee newEmp = employeeMapper.toEmployee(newEmployeeDTO);
        if (newEmployeeDTO.getDepartmentId() != null) {
            Department dept = departmentDAO.findById(newEmployeeDTO.getDepartmentId())
                    .orElseThrow(() -> new NotFoundException(AppMessage.DEPARTMENT_NOT_FOUND));
            newEmp.setDepartment(dept);
        }
        return employeeMapper.toEmployeeResponseDTO(employeeDAO.add(newEmp));
    }

    public EmployeeResponseDTO update(Long id, EmployeeDTO employeeDTO) throws NotFoundException {
        Employee emp = employeeDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(AppMessage.EMPLOYEE_NOT_FOUND));
        if (employeeDTO.getDepartmentId() == null) {
            emp.setDepartment(null);
        } else {
            Department dept = departmentDAO.findById(employeeDTO.getDepartmentId())
                    .orElseThrow(() -> new NotFoundException(AppMessage.DEPARTMENT_NOT_FOUND));
            emp.setDepartment(dept);
        }
        employeeMapper.updateEmployee(emp, employeeDTO);

        return employeeMapper.toEmployeeResponseDTO(emp);
    }

    @Transactional(rollbackOn = {
            RuntimeException.class,
            NotFoundException.class
    })
    public void removeEmployee(Long id) throws NotFoundException {
        Employee emp = employeeDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(AppMessage.EMPLOYEE_NOT_FOUND));
        assignmentDAO.deleteAssignmentsByEmployee(id);
        relativeDAO.deleteRelativesByEmployees(id);
        employeeDAO.delete(emp);
    }
}
