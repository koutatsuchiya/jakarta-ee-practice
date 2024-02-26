package com.jeeproj.company.department.rest;

import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.department.dto.DepartmentDTO;
import com.jeeproj.company.department.entity.Department;
import com.jeeproj.company.department.service.DepartmentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("departments")
@Produces({ MediaType.APPLICATION_JSON })
public class DepartmentResource {
    @Inject
    DepartmentService departmentService;

    @GET
    public Response getDepartments() {
        List<Department> departments = departmentService.getDepartments();
        return Response.ok(departments).build();
    }

    @GET
    @Path("/{id}")
    public Response getDepartmentById(@PathParam("id") Long id) throws NotFoundException {
        DepartmentDTO departmentDTO = departmentService.getDepartmentById(id);
        return Response.ok(departmentDTO).build();
    }

    @POST
    public Response createDepartment(@Valid DepartmentDTO departmentDTO) {
        DepartmentDTO addedDept = departmentService.createDepartment(departmentDTO);
        return Response.ok(addedDept).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateDepartment(@PathParam("id") Long id, @Valid DepartmentDTO updatedDepartment)
            throws NotFoundException {
        DepartmentDTO updatedDepartmentDTO = departmentService.updateDepartment(id, updatedDepartment);

        return Response.ok(updatedDepartmentDTO).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDepartment(@PathParam("id") Long id) {
        departmentService.removeDepartment(id);
        return Response.ok().build();
    }
}
