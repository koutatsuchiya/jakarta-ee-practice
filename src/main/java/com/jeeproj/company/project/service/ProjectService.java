package com.jeeproj.company.project.service;

import com.jeeproj.company.assignment.dao.AssignmentDAO;
import com.jeeproj.company.assignment.entity.Assignment;
import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.base.exception.message.ExceptionMessage;
import com.jeeproj.company.department.dao.DepartmentDAO;
import com.jeeproj.company.department.entity.Department;
import com.jeeproj.company.project.dao.ProjectDAO;
import com.jeeproj.company.project.dto.ProjectDTO;
import com.jeeproj.company.project.dto.ProjectReportDTO;
import com.jeeproj.company.project.dto.ProjectRequestDTO;
import com.jeeproj.company.project.entity.Project;
import com.jeeproj.company.project.service.mapper.ProjectMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
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
        return projectMapper.toProjectDTOs(projectDAO.findAll());
    }

    public ProjectDTO getProjectById(Long id) throws NotFoundException {
        Project project = projectDAO.findProjectById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.PROJECT_NOT_FOUND));

        return projectMapper.toProjectDTO(project);
    }

    public List<ProjectDTO> getAllByDepartmentName(String departmentName) {
        List<Project> projects = projectDAO.findProjectsByDepartmentName(departmentName);

        return projectMapper.toProjectDTOs(projects);
    }

    public ProjectDTO add(ProjectRequestDTO projectRequestDTO) throws NotFoundException {
        Department dept = departmentDAO.findById(projectRequestDTO.getDepartmentId())
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.DEPARTMENT_NOT_FOUND));
        Project newProj = projectMapper.toProject(projectRequestDTO);
        newProj.setDepartment(dept);

        return projectMapper.toProjectDTO(projectDAO.add(newProj));
    }

    public ProjectDTO update(Long id, ProjectRequestDTO projectRequestDTO) throws NotFoundException {
        Project project = projectDAO.findProjectById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.PROJECT_NOT_FOUND));
        if (projectRequestDTO.getDepartmentId() == null) {
            project.setDepartment(null);
        } else {
            Department dept = departmentDAO.findById(projectRequestDTO.getDepartmentId())
                    .orElseThrow(() -> new NotFoundException(ExceptionMessage.DEPARTMENT_NOT_FOUND));
            project.setDepartment(dept);
        }
        projectMapper.updateProject(project, projectRequestDTO);

        return projectMapper.toProjectDTO(project);
    }

    @Transactional(rollbackOn = {
            RuntimeException.class,
            NotFoundException.class
    })
    public void removeProject(Long id) throws NotFoundException {
        Project project = projectDAO.findProjectById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessage.PROJECT_NOT_FOUND));
        assignmentDAO.deleteAssignmentsByProject(id);
        projectDAO.delete(project);
    }

    public List<ProjectReportDTO> findAllByArea(String area) {
        List<ProjectReportDTO> projectReportDTOS = projectMapper
                .toProjectReportDTOs(projectDAO.findProjectsByArea(area));

        return projectReportDTOS.stream().peek(projectDTO -> {
            List<Assignment> assignments = assignmentDAO.findAssignmentsByProjectId(projectDTO.getId());
            projectDTO.setNumberOfHours(assignments.stream()
                    .map(Assignment::getNumberOfHour).reduce(0, Integer::sum));
            projectDTO.setNumberOfEmployees(assignments.size());
            projectDTO.setTotalSalary(assignments.stream()
                    .map(assignment -> assignment.getEmployee().getSalary()).reduce((double) 0, Double::sum));
        }).toList();
    }
}
