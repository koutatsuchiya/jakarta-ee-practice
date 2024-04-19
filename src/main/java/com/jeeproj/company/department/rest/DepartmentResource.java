package com.jeeproj.company.department.rest;

import com.jeeproj.company.base.exception.BadRequestException;
import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.base.filter.Secure;
import com.jeeproj.company.department.dto.DepartmentDTO;
import com.jeeproj.company.department.dto.DepartmentRequestDTO;
import com.jeeproj.company.department.dto.DepartmentRequestsDTO;
import com.jeeproj.company.department.service.DepartmentService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("departments")
@Produces({MediaType.APPLICATION_JSON})
public class DepartmentResource {
    @Inject
    private DepartmentService departmentService;

    @Context
    private UriInfo uriInfo;

    @Context
    private ResourceInfo rsInfo;

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
    @Consumes({MediaType.APPLICATION_JSON})
    @Secure()
    @RolesAllowed({"ADMIN"})
    public Response add(@Valid DepartmentRequestDTO departmentRequestDTO) throws BadRequestException {
        DepartmentDTO addedDept = departmentService.add(departmentRequestDTO);
        URI location = uriInfo.getAbsolutePathBuilder().path(addedDept.getId().toString()).build();

        return Response.created(location).entity(addedDept).build();
    }

    @POST
    @Path("/bulk")
    @Consumes({MediaType.APPLICATION_JSON})
    @Secure()
    @RolesAllowed({"ADMIN"})
    public Response addDepartments(@Valid DepartmentRequestsDTO departmentRequestsDTO) throws BadRequestException {
        List<DepartmentDTO> deptToAdds = departmentService.addDepartments(departmentRequestsDTO);
        String rsPath = rsInfo.getResourceClass().getAnnotation(Path.class).value();
        String location = String.join("; ",
                deptToAdds.stream().map(deptToAdd -> uriInfo.getBaseUriBuilder()
                        .path(rsPath)
                        .path(deptToAdd.getId().toString()).build().toString()).toList());

        return Response.status(Response.Status.CREATED)
                .header(HttpHeaders.LOCATION, location)
                .entity(deptToAdds)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Secure()
    @RolesAllowed({"ADMIN"})
    public Response updateDepartment(@PathParam("id") Long id, @Valid DepartmentRequestDTO updatedDepartment)
            throws NotFoundException {
        DepartmentDTO updatedDepartmentDTO = departmentService.update(id, updatedDepartment);

        return Response.ok(updatedDepartmentDTO).build();
    }

    @DELETE
    @Path("/{id}")
    @Secure()
    @RolesAllowed({"ADMIN"})
    public Response deleteDepartment(@PathParam("id") Long id) throws NotFoundException {
        departmentService.removeDepartment(id);
        return Response.noContent().build();
    }
}
