package com.jeeproj.company.user.rest;

import com.jeeproj.company.base.exception.BadRequestException;
import com.jeeproj.company.base.filter.Secure;
import com.jeeproj.company.user.dto.SignUpDTO;
import com.jeeproj.company.user.dto.UserDTO;
import com.jeeproj.company.user.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
@Produces({MediaType.APPLICATION_JSON})
public class UserResource {
    @Inject
    private UserService userService;

    @GET
    @Secure
    public Response getAllUsers() {
        return Response.ok().entity(userService.getAllUsers()).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response signUp(@Valid SignUpDTO signUpDTO) throws BadRequestException {
        UserDTO userDTO = userService.signUpUser(signUpDTO);
        return Response.ok(userDTO).build();
    }
}
