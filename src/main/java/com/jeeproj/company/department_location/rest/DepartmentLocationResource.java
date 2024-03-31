package com.jeeproj.company.department_location.rest;

import com.jeeproj.company.base.exception.BadRequestException;
import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.base.filter.Secure;
import com.jeeproj.company.department_location.dto.DepartmentLocationDTO;
import com.jeeproj.company.department_location.dto.DepartmentLocationRequestDTO;
import com.jeeproj.company.department_location.service.DepartmentLocationService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("locations")
@Produces({MediaType.APPLICATION_JSON})
public class DepartmentLocationResource {
    @Inject
    private DepartmentLocationService departmentLocationService;

    @Context
    private UriInfo uriInfo;

    @GET
    public Response findAll() {
        List<DepartmentLocationDTO> departmentLocationDTOs = departmentLocationService.getAll();
        return Response.ok(departmentLocationDTOs).build();
    }

    @GET
    @Path("/{id}")
    @Secure
    public Response findLocationById(@PathParam("id") Long id) throws NotFoundException {
        DepartmentLocationDTO departmentLocationDTO = departmentLocationService.getLocationById(id);
        return Response.ok(departmentLocationDTO).build();
    }

    @GET
    @Path("departments/{departmentId}")
    @Secure
    public Response findDepartmentLocationByDepartmentId(@PathParam("departmentId") Long departmentId)
            throws NotFoundException {
        List<DepartmentLocationDTO> departmentLocationDTOs = departmentLocationService
                .findLocationsByDepartmentId(departmentId);
        return Response.ok(departmentLocationDTOs).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Secure
    public Response add(@Valid DepartmentLocationRequestDTO departmentLocationRequestDTO)
            throws BadRequestException, NotFoundException {
        DepartmentLocationDTO departmentLocationDTO = departmentLocationService.add(departmentLocationRequestDTO);
        URI location = uriInfo.getAbsolutePathBuilder().path(departmentLocationDTO.getId().toString()).build();

        return Response.created(location).entity(departmentLocationDTO).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Secure
    public Response update(@PathParam("id") Long id, @Valid DepartmentLocationRequestDTO departmentLocationRequestDTO)
            throws BadRequestException, NotFoundException {
        DepartmentLocationDTO departmentLocationDTO = departmentLocationService.update(id, departmentLocationRequestDTO);

        return Response.ok(departmentLocationDTO).build();
    }

    @DELETE
    @Path("/{id}")
    @Secure
    public Response delete(@PathParam("id") Long id) throws NotFoundException {
        departmentLocationService.delete(id);

        return Response.noContent().build();
    }
}
