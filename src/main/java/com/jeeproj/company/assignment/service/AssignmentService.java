package com.jeeproj.company.assignment.service;

import com.jeeproj.company.assignment.dao.AssignmentDAO;
import com.jeeproj.company.assignment.dto.*;
import com.jeeproj.company.assignment.entity.Assignment;
import com.jeeproj.company.assignment.service.mapper.AssignmentMapper;
import com.jeeproj.company.base.exception.BadRequestException;
import com.jeeproj.company.base.exception.NotFoundException;
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
                .orElseThrow(() -> new NotFoundException("Assignment not found"));

        return assignmentMapper.toAssignmentDTO(assignment);
    }

    public AssignmentDTO add(CreateAssignmentRequestDTO createAssignmentRequestDTO)
            throws NotFoundException, BadRequestException {
        Employee employee = employeeDAO
                .findActiveEmployeeById(createAssignmentRequestDTO.getEmployeeId())
                .orElseThrow(() -> new NotFoundException("Employee not found"));

        Project project = projectDAO
                .findActiveProjectById(createAssignmentRequestDTO.getProjectId())
                .orElseThrow(() -> new NotFoundException("Project not found"));

        Optional<Assignment> optionalAssignment = assignmentDAO
                .findAssignmentByEmployeeIdAndProjectId(employee.getId(), project.getId());

        if(optionalAssignment.isPresent()) {
            throw new BadRequestException("Assignment existed");
        }
        Assignment newAssignment = Assignment
                .builder()
                .employee(employee)
                .project(project)
                .numberOfHour(createAssignmentRequestDTO.getNumberOfHour())
                .build();
        Assignment assignment = assignmentDAO.add(newAssignment);

        return assignmentMapper.toAssignmentDTO(assignment);
    }
}
