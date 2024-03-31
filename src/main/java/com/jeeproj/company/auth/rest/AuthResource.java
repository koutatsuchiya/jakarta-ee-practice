package com.jeeproj.company.auth.rest;

import com.jeeproj.company.auth.dto.LoginRequestDTO;
import com.jeeproj.company.auth.service.AuthService;
import com.jeeproj.company.base.message.AppMessage;
import com.jeeproj.company.base.response.ResponseBody;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
@Produces({MediaType.APPLICATION_JSON})
public class AuthResource {
    @Inject
    AuthService authService;

    @POST
    @Path("login")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response login(@Valid LoginRequestDTO loginDTO) throws Exception {
        return Response.ok().entity(new ResponseBody(
                        Response.Status.OK.getStatusCode(),
                        AppMessage.LOGIN_SUCCESSFULLY,
                        authService.login(loginDTO)
        )).build();
    }
}
