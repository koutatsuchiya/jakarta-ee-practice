package com.jeeproj.company.project.service;

import com.jeeproj.company.assignment.dao.AssignmentDAO;
import com.jeeproj.company.assignment.entity.Assignment;
import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.department.dao.DepartmentDAO;
import com.jeeproj.company.project.dao.ProjectDAO;
import com.jeeproj.company.project.dto.ProjectDTO;
import com.jeeproj.company.project.entity.Project;
import com.jeeproj.company.project.service.mapper.ProjectMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProjectService {
    @Inject
    private ProjectDAO projectDAO;

    @Inject
    private AssignmentDAO assignmentDAO;

    @Inject
    private DepartmentDAO departmentDAO;

    @Inject
    private ProjectMapper projectMapper;

    public List<ProjectDTO> getAll() {
        List<Project> projects = this.projectDAO.findAll();
        return this.projectMapper.toProjectDTOs(projects);
    }

    public ProjectDTO getProjectById(Long id) throws NotFoundException {
        Project project = this.projectDAO.findActiveProjectById(id)
                .orElseThrow(() -> new javax.ws.rs.NotFoundException("Project not found"));

        return projectMapper.toProjectDTO(project);
    }

    public List<ProjectDTO> findAllByDepartmentName(String departmentName) {
        List<Project> projects = projectDAO.findAll();

        return projectMapper
                .toProjectDTOs(projects.stream().filter(project -> project.getDepartment().getDepartmentName()
                .equals(departmentName)).toList());
    }

    public List<ProjectDTO> findAllByArea(String area) {
        List<ProjectDTO> projectDTOs = projectMapper.toProjectDTOs(projectDAO.findProjectsByArea(area));

        return projectDTOs.stream().peek(projectDTO -> {
            List<Assignment> assignments = assignmentDAO.findAssignmentsByProjectId(projectDTO.getId());
            projectDTO.setNumberOfHours(assignments.stream()
                    .map(Assignment::getNumberOfHour).reduce(0, Integer::sum));
            projectDTO.setNumberOfEmployees(assignments.size());
            projectDTO.setTotalSalary(assignments.stream()
                    .map(assignment -> assignment.getEmployee().getSalary()).reduce(0, Integer::sum));
        }).toList();
    }

}
