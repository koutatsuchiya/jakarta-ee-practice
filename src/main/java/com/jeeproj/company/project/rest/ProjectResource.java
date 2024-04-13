package com.jeeproj.company.project.rest;

import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.base.filter.Secure;
import com.jeeproj.company.project.dto.ProjectDTO;
import com.jeeproj.company.project.dto.ProjectReportDTO;
import com.jeeproj.company.project.dto.ProjectRequestDTO;
import com.jeeproj.company.project.service.ProjectService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("projects")
@Produces({MediaType.APPLICATION_JSON})
public class ProjectResource {
    @Inject
    private ProjectService projectService;

    @Context
    private UriInfo uriInfo;

    @GET
    public Response findAll() {
        List<ProjectDTO> projectDTOS = projectService.getAll();
        return Response.ok(projectDTOS).build();
    }

    @GET
    @Path("/{id}")
    @Secure
    public Response findById(@PathParam("id") Long id) throws NotFoundException {
        ProjectDTO projectDTO = projectService.getProjectById(id);
        return Response.ok(projectDTO).build();
    }

    @GET
    @Path("/departments")
    @Secure
    public Response findProjectsByDepartmentName(
            @DefaultValue("department") @QueryParam("departmentName") String departmentName) {
        List<ProjectDTO> projectDTOs = projectService.getAllByDepartmentName(departmentName);

        return Response.ok(projectDTOs).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Secure()
    @RolesAllowed({"ADMIN"})
    public Response createProject(@Valid ProjectRequestDTO projectRequestDTO) throws NotFoundException {
        ProjectDTO projectDTO = projectService.add(projectRequestDTO);
        URI location = uriInfo.getAbsolutePathBuilder().path(projectDTO.getId().toString()).build();

        return Response.created(location).entity(projectDTO).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Secure()
    @RolesAllowed({"ADMIN"})
    public Response updateProject(@PathParam("id") Long id, @Valid ProjectRequestDTO projectRequestDTO)
            throws NotFoundException {
        ProjectDTO projectDTO = projectService.update(id, projectRequestDTO);

        return Response.ok(projectDTO).build();
    }

    @DELETE
    @Path("/{id}")
    @Secure()
    @RolesAllowed({"ADMIN"})
    public Response deleteProject(@PathParam("id") Long id) throws NotFoundException {
        projectService.removeProject(id);

        return Response.noContent().build();
    }

    @GET
    @Path("/areas")
    public Response findProjectsByArea(@DefaultValue("area") @QueryParam("area") String area) {
        List<ProjectReportDTO> projectReportDTOs = projectService.findAllByArea(area);

        return Response.ok(projectReportDTOs).build();
    }
}
