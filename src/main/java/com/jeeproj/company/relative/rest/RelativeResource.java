package com.jeeproj.company.relative.rest;

import com.jeeproj.company.relative.dto.RelativeDTO;
import com.jeeproj.company.relative.service.RelativeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("relatives")
public class RelativeResource {
    @Inject
    private RelativeService relativeService;

    @GET
    @Path("employee/{employeeId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRelativesByEmployeeId(@PathParam("employeeId") Long employeeId) {
        List<RelativeDTO> relativeDTOs = relativeService.findRelativesByEmployeeId(employeeId);
        return Response.ok().entity(relativeDTOs).build();
    }

    @GET
    @Path("department/{departmentId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findRelativesByDepartment(@PathParam("departmentId") Long departmentId) {
        List<RelativeDTO> relatives = relativeService.findRelativesByDepartment(departmentId);
        return Response.ok().entity(relatives).build();
    }
}
