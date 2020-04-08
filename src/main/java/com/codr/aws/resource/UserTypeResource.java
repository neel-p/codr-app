package com.codr.aws.resource;

import com.codr.aws.config.JdbiDataSource;
import com.codr.aws.dto.ApiResponse;
import com.codr.aws.repo.UserTypeRepo;
import com.codr.aws.service.UserTypeService;
import com.codr.aws.repo.impl.UserTypeRepoImpl;
import com.codr.aws.util.AppUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/userType")
public class UserTypeResource {

    private final UserTypeRepo userTypeRepo;
    private final UserTypeService userTypeService;
    private static final Logger logger = LoggerFactory.getLogger(UserTypeResource.class);

    public UserTypeResource(UserTypeRepo repo, UserTypeService service) {
        this.userTypeRepo = repo;
        this.userTypeService = service;
    }

    public UserTypeResource() {
        this.userTypeRepo = new UserTypeRepoImpl(JdbiDataSource.get());;
        this.userTypeService = new UserTypeService(userTypeRepo);
    }

    @GET
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserTypes() {
        ApiResponse response = new ApiResponse();
        try {
            response.add("user_type", userTypeService.getUserTypes());
        } catch (Exception e) {
            AppUtils.logEx(logger, response, e, "Unable to retrieve users list at this moment.");
        }
        return AppUtils.buildResponse(response);
    }
}
