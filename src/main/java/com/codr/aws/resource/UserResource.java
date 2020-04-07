package com.codr.aws.resource;

import com.codr.aws.dto.ApiResponse;
import com.codr.aws.repo.impl.UserRepoImpl;
import com.codr.aws.service.UserService;
import com.codr.aws.config.JdbiDataSource;
import com.codr.aws.dto.User;
import com.codr.aws.exceptions.BadInputException;
import com.codr.aws.repo.UserRepo;
import com.codr.aws.util.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {

    private final UserRepo repo;
    private final UserService service;
    private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(User user) {
        ApiResponse response = new ApiResponse();
        try {
            String guid = service.save(user);
            response.add("guid", guid);
        } catch (BadInputException be) {
            AppUtils.logEx(logger, response, be, "Failed to save user due to input validation failure.");
        } catch (Exception e) {
            AppUtils.logEx(logger, response, e, "Failed to save user at this moment.");
        }
        return AppUtils.buildResponse(response);
    }

    public UserResource(UserRepo repo, UserService service) {
        this.repo = repo;
        this.service = service;
    }

    public UserResource() {
        this.repo = new UserRepoImpl(JdbiDataSource.get());;
        this.service = new UserService(repo);
    }

    @GET
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        ApiResponse response = new ApiResponse();
        try {
            response.add("users", service.getUsers());
        } catch (Exception e) {
            AppUtils.logEx(logger, response, e, "Unable to retrieve users list at this moment.");
        }
        return AppUtils.buildResponse(response);
    }

    @GET
    @Path("/{guid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersByGuid(@PathParam("guid") String guid) {
        ApiResponse response = new ApiResponse();
        try {
            response.add("users", service.getUserByGuid(guid));
        } catch (Exception e) {
            AppUtils.logEx(logger, response, e, "Unable to retrieve user at this moment.");
        }
        return AppUtils.buildResponse(response);
    }
}
