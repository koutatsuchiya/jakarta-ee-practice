package com.jeeproj.company.employee.rest;

import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.employee.dto.EmployeeRequestDTO;
import com.jeeproj.company.employee.dto.EmployeeResponseDTO;
import com.jeeproj.company.employee.entity.Employee;
import com.jeeproj.company.employee.service.EmployeeService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;


@Path("employees")
@Produces({MediaType.APPLICATION_JSON})
public class EmployeeResource {
    @Inject
    EmployeeService employeeService;

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

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createEmp(@Valid EmployeeRequestDTO newEmp) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.createEmployee(newEmp);

        return Response.ok(employeeResponseDTO).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateEmployee(@PathParam("id") long id, @Valid EmployeeRequestDTO updatedEmployee)
            throws NotFoundException {
        Employee updatedEmployeeEntity = employeeService.updateEmployee(id, updatedEmployee);

        return Response.ok(updatedEmployeeEntity).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEmployee(@PathParam("id") long id) {
        employeeService.removeEmployee(id);

        return Response.ok().build();
    }

//    @GET
//    public Response getEmployeesByDepartmentID(@PathParam("departmentID") Long id)  {
//        try {
//            return Response.ok(employeeService.get(id)).build();
//        } catch (EntityNotFoundException e) {
//            return new AppExceptionMapper().toResponse(e);
//        }
//    }
}
