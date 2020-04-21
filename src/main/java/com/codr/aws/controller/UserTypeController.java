package com.codr.aws.controller;

import com.codr.aws.config.JdbiDataSource;
import com.codr.aws.dto.ApiResponse;
import com.codr.aws.repo.UserTypeRepo;
import com.codr.aws.repo.impl.UserTypeRepoImpl;
import com.codr.aws.service.UserTypeService;
import com.codr.aws.util.AppUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/userType")
public class UserTypeController {

    private final UserTypeRepo userTypeRepo;
    private final UserTypeService userTypeService;
    private static final Logger logger = LoggerFactory.getLogger(UserTypeController.class);

    public UserTypeController(UserTypeRepo repo, UserTypeService service) {
        this.userTypeRepo = repo;
        this.userTypeService = service;
    }

    public UserTypeController() {
        this.userTypeRepo = new UserTypeRepoImpl(JdbiDataSource.get());;
        this.userTypeService = new UserTypeService(userTypeRepo);
    }

    @GET
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserTypes() throws JsonProcessingException {
        ApiResponse response = new ApiResponse();
        try {
            response.add("user_type", userTypeService.getUserTypes());
        } catch (Exception e) {
            AppUtils.logEx(logger, response, e, "Unable to retrieve users list at this moment.");
        }
        return AppUtils.buildResponse(response);
    }
}
