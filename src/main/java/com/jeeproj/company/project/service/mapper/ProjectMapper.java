package com.jeeproj.company.project.service.mapper;

import com.jeeproj.company.project.dto.ProjectDTO;
import com.jeeproj.company.project.entity.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface ProjectMapper {
    ProjectDTO toProjectDTO(Project project);

    Project toProject(ProjectDTO projectDTO);

    List<ProjectDTO> toProjectDTOs(List<Project> projects);
}
