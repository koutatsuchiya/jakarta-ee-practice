package com.jeeproj.company.department.rest;

import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.base.filter.Secure;
import com.jeeproj.company.department.dto.DepartmentDTO;
import com.jeeproj.company.department.service.DepartmentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("departments")
@Produces({ MediaType.APPLICATION_JSON })
public class DepartmentResource {
    @Inject
    DepartmentService departmentService;

    @Context
    private UriInfo uriInfo;

    @GET
    public Response getDepartments() {
        List<DepartmentDTO> departments = departmentService.getDepartments();
        return Response.ok(departments).build();
    }

    @GET
    @Path("/{id}")
    @Secure
    public Response getDepartmentById(@PathParam("id") Long id) throws NotFoundException {
        DepartmentDTO departmentDTO = departmentService.getDepartmentById(id);
        return Response.ok(departmentDTO).build();
    }

    @POST
    @Secure(role = "ADMIN")
    public Response add(@Valid DepartmentDTO departmentDTO) {
        DepartmentDTO addedDept = departmentService.add(departmentDTO);
        URI location = uriInfo.getAbsolutePathBuilder().path(addedDept.getId().toString()).build();

        return Response.created(location).entity(addedDept).build();
    }

    @PUT
    @Path("/{id}")
    @Secure(role = "ADMIN")
    public Response updateDepartment(@PathParam("id") Long id, @Valid DepartmentDTO updatedDepartment)
            throws NotFoundException {
        DepartmentDTO updatedDepartmentDTO = departmentService.update(id, updatedDepartment);

        return Response.ok(updatedDepartmentDTO).build();
    }

    @DELETE
    @Path("/{id}")
    @Secure(role = "ADMIN")
    public Response deleteDepartment(@PathParam("id") Long id) throws NotFoundException {
        departmentService.removeDepartment(id);
        return Response.noContent().build();
    }
}
