package com.jeeproj.company.project.rest;

import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.project.dto.ProjectDTO;
import com.jeeproj.company.project.service.ProjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("projects")
@Produces({MediaType.APPLICATION_JSON})
public class ProjectResource {
    @Inject
    private ProjectService projectService;

    @GET
    public Response findAll() {
        List<ProjectDTO> projectDTOS = projectService.getAll();
        return Response.ok().entity(projectDTOS).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) throws NotFoundException {
        ProjectDTO projectDTO = projectService.getProjectById(id);
        return Response.ok().entity(projectDTO).build();
    }

    @GET
    @Path("/department")
    public Response findProjectsByDepartmentName(@DefaultValue("department") @QueryParam("departmentName") String departmentName) {
        List<ProjectDTO> projectListResponseDTO = projectService.findAllByDepartmentName(departmentName);
        System.out.println(projectListResponseDTO.size());
        return Response.ok().entity(projectListResponseDTO).build();
    }

    @GET
    @Path("/area")
    public Response findProjectsByArea(@DefaultValue("area") @QueryParam("area") String area) {
        List<ProjectDTO> projectListResponseDTO = projectService.findAllByArea(area);
        System.out.println(projectListResponseDTO.size());
        return Response.ok().entity(projectListResponseDTO).build();
    }
}
