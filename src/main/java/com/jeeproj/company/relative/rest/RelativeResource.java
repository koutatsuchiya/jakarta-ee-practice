package com.jeeproj.company.relative.rest;

import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.base.filter.Secure;
import com.jeeproj.company.relative.dto.RelativeDTO;
import com.jeeproj.company.relative.dto.RelativeRequestDTO;
import com.jeeproj.company.relative.service.RelativeService;

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

@Path("relatives")
@Produces({MediaType.APPLICATION_JSON})
public class RelativeResource {
    @Inject
    private RelativeService relativeService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Secure
    public Response findAll() {
        return Response.ok(relativeService.getAll()).build();
    }

    @GET
    @Path("/{id}")
    @Secure
    public Response findRelativeById(@PathParam("id") Long id) throws NotFoundException {
        RelativeDTO relativeDTO = relativeService.getRelativeById(id);
        return Response.ok(relativeDTO).build();
    }

    @GET
    @Path("employees/{employeeId}")
    @Secure
    public Response findRelativesByEmployeeId(@PathParam("employeeId") Long employeeId) {
        List<RelativeDTO> relativeDTOs = relativeService.findRelativesByEmployeeId(employeeId);
        return Response.ok(relativeDTOs).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Secure()
    @RolesAllowed({"ADMIN"})
    public Response add(@Valid RelativeRequestDTO relativeRequestDTO) throws NotFoundException {
        RelativeDTO relativeDTO = relativeService.add(relativeRequestDTO);
        URI location = uriInfo.getAbsolutePathBuilder().path(relativeDTO.getId().toString()).build();

        return Response.created(location).entity(relativeDTO).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Secure()
    @RolesAllowed({"ADMIN"})
    public Response updateRelative(@PathParam("id") Long id, @Valid RelativeRequestDTO relativeRequestDTO)
            throws NotFoundException {
        RelativeDTO relativeDTO = relativeService.update(id, relativeRequestDTO);
        return Response.ok(relativeDTO).build();
    }

    @DELETE
    @Path("/{id}")
    @Secure()
    @RolesAllowed({"ADMIN"})
    public Response deleteRelative(@PathParam("id") Long id) throws NotFoundException {
        relativeService.removeRelative(id);
        return Response.noContent().build();
    }

    @GET
    @Path("departments/{departmentId}")
    @Secure
    public Response findRelativesByDepartment(@PathParam("departmentId") Long departmentId) {
//        List<RelativeDTO> relativeDTOs = relativeService.findRelativeDTOsByDepartment(departmentId);
        List<RelativeDTO> relativeDTOs = relativeService.findRelativesByDepartment(departmentId);
        return Response.ok(relativeDTOs).build();
    }
}
