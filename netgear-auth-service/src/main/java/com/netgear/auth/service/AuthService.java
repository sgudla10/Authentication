package com.netgear.auth.service;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.netgear.auth.dto.AuthRequest;

@Path("/user")
public interface AuthService {

    @POST
    @Path("/login")
    @Produces({APPLICATION_JSON})
    @Consumes({APPLICATION_JSON})
    public Response authenticate(AuthRequest request);

}
