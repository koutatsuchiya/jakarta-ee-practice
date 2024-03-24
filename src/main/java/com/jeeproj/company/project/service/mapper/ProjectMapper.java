package com.jeeproj.company.project.service.mapper;

import com.jeeproj.company.project.dto.ProjectDTO;
import com.jeeproj.company.project.dto.ProjectReportDTO;
import com.jeeproj.company.project.dto.ProjectRequestDTO;
import com.jeeproj.company.project.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface ProjectMapper {
    ProjectRequestDTO toProjectRequestDTO(Project project);

    ProjectDTO toProjectDTO(Project project);

    Project toProject(ProjectRequestDTO projectRequestDTO);

    ProjectReportDTO toProjectReportDTO(Project project);

    List<ProjectDTO> toProjectDTOs(List<Project> projects);

    List<ProjectReportDTO> toProjectReportDTOs(List<Project> projects);

    void updateProject(@MappingTarget Project project, ProjectRequestDTO projectRequestDTO);
}
