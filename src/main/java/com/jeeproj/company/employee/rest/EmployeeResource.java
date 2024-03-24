package com.jeeproj.company.employee.rest;

import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.employee.dto.EmployeeDTO;
import com.jeeproj.company.employee.dto.EmployeeResponseDTO;
import com.jeeproj.company.employee.service.EmployeeService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("employees")
@Produces({MediaType.APPLICATION_JSON})
public class EmployeeResource {
    @Inject
    EmployeeService employeeService;

    @Context
    private UriInfo uriInfo;

    @GET
    public Response getEmployees() {
        List<EmployeeResponseDTO> employees = employeeService.getEmployees();
        return Response.ok(employees).build();
    }

    @GET
    @Path("/{id}")
    public Response getEmployeeById(@PathParam("id") Long id) throws NotFoundException {
        return Response.ok(employeeService.getEmployeeById(id)).build();
    }

    @GET
    @Path("/departments/{id}")
    public Response getEmployeesByDepartmentID(@PathParam("departmentID") Long id) throws NotFoundException {
        return Response.ok(employeeService.getEmployeesByDepartment(id)).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response add(@Valid EmployeeDTO newEmp) throws NotFoundException {
        EmployeeResponseDTO employeeResponseDTO = employeeService.add(newEmp);
        URI location = uriInfo.getAbsolutePathBuilder().path(employeeResponseDTO.getId().toString()).build();

        return Response.created(location).entity(employeeResponseDTO).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateEmployee(@PathParam("id") Long id, @Valid EmployeeDTO updateEmployeeRequestDTO)
            throws NotFoundException {
        EmployeeResponseDTO updatedEmployee = employeeService.update(id, updateEmployeeRequestDTO);

        return Response.ok(updatedEmployee).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") Long id) throws NotFoundException {
        employeeService.removeEmployee(id);

        return Response.noContent().build();
    }
}
