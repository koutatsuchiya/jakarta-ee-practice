package com.jeeproj.company.assignment.rest;

import com.jeeproj.company.assignment.dto.*;
import com.jeeproj.company.assignment.service.AssignmentService;
import com.jeeproj.company.base.exception.BadRequestException;
import com.jeeproj.company.base.exception.NotFoundException;

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
        return Response.ok().entity(assignmentDTOs).build();
    }

    @GET
    @Path("/{id}")
    public Response getAssignmentById(@PathParam("id") Long id) throws NotFoundException {
        return Response.ok(assignmentService.getAssignmentById(id)).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response add(@Valid CreateAssignmentRequestDTO requestDTO)
            throws BadRequestException, NotFoundException {
        AssignmentDTO assignmentDTO = assignmentService.add(requestDTO);

        String path = String.format("%s/%d", uriInfo.getAbsolutePath().getPath(), assignmentDTO.getId());

        return Response.created(URI.create(path)).entity(assignmentDTO).build();
    }
}
