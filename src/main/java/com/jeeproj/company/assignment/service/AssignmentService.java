package com.jeeproj.company.assignment.service;

import com.jeeproj.company.assignment.dao.AssignmentDAO;
import com.jeeproj.company.assignment.dto.*;
import com.jeeproj.company.assignment.entity.Assignment;
import com.jeeproj.company.assignment.service.mapper.AssignmentMapper;
import com.jeeproj.company.base.exception.BadRequestException;
import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.base.exception.message.ExceptionMessage;
import com.jeeproj.company.employee.dao.EmployeeDAO;
import com.jeeproj.company.employee.entity.Employee;
import com.jeeproj.company.project.dao.ProjectDAO;
import com.jeeproj.company.project.entity.Project;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
public class AssignmentService {
    @Inject
    private AssignmentDAO assignmentDAO;

    @Inject
    private ProjectDAO projectDAO;

    @Inject
    private EmployeeDAO employeeDAO;

    @Inject
    private AssignmentMapper assignmentMapper;

    public List<AssignmentDTO> getAll() {
        return assignmentMapper.toAssignmentDTOs(assignmentDAO.findAll());
    }

    public AssignmentDTO getAssignmentById(Long id) throws NotFoundException {
        Assignment assignment = assignmentDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.ASSIGNMENT_NOT_FOUND));

        return assignmentMapper.toAssignmentDTO(assignment);
    }

    public AssignmentDTO add(AssignmentRequestDTO assignmentRequestDTO)
            throws NotFoundException, BadRequestException {
        Project project = projectDAO.findProjectById(assignmentRequestDTO.getProjectId())
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.PROJECT_NOT_FOUND));
        Employee employee = employeeDAO.findEmployeeById(assignmentRequestDTO.getEmployeeId())
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.EMPLOYEE_NOT_FOUND));
        Optional<Assignment> existedAssignment = assignmentDAO
                .findAssignmentByProjectAndEmployee(project.getId(), employee.getId());
        if(existedAssignment.isPresent()) {
            throw new BadRequestException(ExceptionMessage.ASSIGNMENT_EXIST);
        }
        Assignment newAssignment = assignmentMapper.toAssignment(assignmentRequestDTO);
        newAssignment.setProject(project);
        newAssignment.setEmployee(employee);

        return assignmentMapper.toAssignmentDTO(assignmentDAO.add(newAssignment));
    }

    public AssignmentDTO update(Long id, AssignmentRequestDTO assignmentRequestDTO) throws NotFoundException {
        Assignment assignment = assignmentDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.ASSIGNMENT_NOT_FOUND));
        Project project = projectDAO.findProjectById(assignmentRequestDTO.getProjectId())
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.PROJECT_NOT_FOUND));
        Employee employee = employeeDAO.findEmployeeById(assignmentRequestDTO.getEmployeeId())
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.EMPLOYEE_NOT_FOUND));
        assignment.setProject(project);
        assignment.setEmployee(employee);
        assignmentMapper.updateAssignment(assignment, assignmentRequestDTO);

        return assignmentMapper.toAssignmentDTO(assignment);
    }

    public void removeAssignment(Long id) throws NotFoundException {
        Assignment assignment = assignmentDAO.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.ASSIGNMENT_NOT_FOUND));
        assignmentDAO.delete(assignment);
    }
}
