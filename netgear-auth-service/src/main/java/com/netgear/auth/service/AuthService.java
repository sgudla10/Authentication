package com.netgear.auth.service;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;

import com.netgear.auth.dto.AuthRequest;

@Controller
//@Path("/user")
//@RequestMapping("/user")
public interface AuthService {

    //@POST
    //@Path("/login")
    //@RequestMapping("/login")
    //@Produces({APPLICATION_JSON})
    //@Consumes({APPLICATION_JSON})
    public Response authenticate(AuthRequest request);

}
