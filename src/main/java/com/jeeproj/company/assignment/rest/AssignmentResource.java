package com.jeeproj.company.assignment.rest;

import com.jeeproj.company.assignment.dto.*;
import com.jeeproj.company.assignment.service.AssignmentService;
import com.jeeproj.company.base.exception.BadRequestException;
import com.jeeproj.company.base.exception.NotFoundException;
import com.jeeproj.company.base.filter.Secure;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("assignments")
@Produces({MediaType.APPLICATION_JSON})
public class AssignmentResource {
    @Inject
    private AssignmentService assignmentService;

    @Context
    private UriInfo uriInfo;

    @GET
    public Response findAssignments() {
        List<AssignmentDTO> assignmentDTOs = assignmentService.getAll();
        return Response.ok(assignmentDTOs).build();
    }

    @GET
    @Path("/{id}")
    public Response getAssignmentById(@PathParam("id") Long id) throws NotFoundException {
        return Response.ok(assignmentService.getAssignmentById(id)).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Secure(role = "ADMIN")
    public Response add(@Valid AssignmentRequestDTO requestDTO)
            throws BadRequestException, NotFoundException {
        AssignmentDTO assignmentDTO = assignmentService.add(requestDTO);
        URI location = uriInfo.getAbsolutePathBuilder().path(assignmentDTO.getId().toString()).build();

        return Response.created(location).entity(assignmentDTO).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Secure(role = "ADMIN")
    public Response update(@PathParam("id") Long id, @Valid AssignmentRequestDTO requestDTO) throws NotFoundException {
        AssignmentDTO assignmentDTO = assignmentService.update(id, requestDTO);
        return Response.ok(assignmentDTO).build();
    }

    @DELETE
    @Path("/{id}")
    @Secure(role = "ADMIN")
    public Response deleteAssignment(@PathParam("id") Long id) throws NotFoundException {
        assignmentService.removeAssignment(id);
        return Response.noContent().build();
    }
}
