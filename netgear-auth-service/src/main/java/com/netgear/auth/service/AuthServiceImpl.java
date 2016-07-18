package com.netgear.auth.service;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netgear.auth.dao.CassandraManger;
import com.netgear.auth.dao.UserDao;
import com.netgear.auth.dto.AuthRequest;
import com.netgear.auth.dto.AuthResponse;
import com.netgear.auth.dto.Error;
import com.netgear.auth.entity.UserDO;
import com.netgear.auth.service.helper.ServiceHelper;

@RestController
public class AuthServiceImpl {

    private static final Logger LOG = LoggerFactory.getLogger(CassandraManger.class);

    @Autowired
    private UserDao<UserDO> userDao;

    @Autowired
    private ServiceHelper helper;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        Error error = new Error();
        AuthResponse response = new AuthResponse();
        try {
            LOG.info("Receivied the request for authentication ");
            boolean isInValidRequest = helper.validate(request, error);
            if (isInValidRequest) {
                response.addError(error);
                ResponseEntity entity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                return entity;
            }
            UserDO userDo = userDao.find(UserDO.class, request.getEmail());

            if (userDo == null) {
                error.setCode("400.AuthService.NOT_FOUND");
                error.setDesc("user not found");
                response.addError(error);
                ResponseEntity entity = new ResponseEntity(response, HttpStatus.NOT_FOUND);
                return entity;
            }
            if (!(userDo.getPassword().equals(request.getPassword()))) {
                error.setCode("400.AuthService.BAD_REQUEST");
                error.setDesc("Password is incorrect");
                response.addError(error);
                ResponseEntity entity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                return entity;
                //return constructResponse(response, Response.Status.BAD_REQUEST);

            }
            response = helper.convertDOtoDTO(userDo);
            ResponseEntity entity = new ResponseEntity(response, HttpStatus.OK);
            return entity;
            //return constructResponse(response, Response.Status.OK);

        } catch (Exception e) {
            LOG.error("Failed while procesing the request ", e);
            error.setCode("500.AuthService.SYSTEM_ERROR");
            error.setDesc(e.getMessage());
            response.addError(error);
            helper.convertDOtoDTO(null);
            ResponseEntity entity = new ResponseEntity(response, HttpStatus.OK);
            return entity;
            //return constructResponse(response, Response.Status.BAD_REQUEST);

        }
    }

    private Response constructResponse(AuthResponse response, Response.Status status) {
        ResponseBuilder builder = Response.status(status);
        builder.entity(response);
        return builder.build();
    }
}
