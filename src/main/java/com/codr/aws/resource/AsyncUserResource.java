package com.codr.aws.resource;

import com.codr.aws.dto.ApiResponse;
import com.codr.aws.repo.impl.AsyncRequestRepoImpl;
import com.codr.aws.config.JdbiDataSource;
import com.codr.aws.dto.User;
import com.codr.aws.enums.AsyncRequestResourceType;
import com.codr.aws.enums.AsyncRequestStatus;
import com.codr.aws.exceptions.BadInputException;
import com.codr.aws.repo.AsyncRequestRepo;
import com.codr.aws.service.AsyncUserService;
import com.codr.aws.util.AppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.ACCEPTED;
import static javax.ws.rs.core.Response.Status.FOUND;

@Path("/users/async")
public class AsyncUserResource {

    private final AsyncRequestRepo repo;
    private final AsyncUserService service;
    private static final Logger logger = LoggerFactory.getLogger(AsyncUserResource.class);

    public AsyncUserResource(AsyncRequestRepo repo, AsyncUserService service) {
        this.repo = repo;
        this.service = service;
    }

    public AsyncUserResource() {
        this.repo = new AsyncRequestRepoImpl(JdbiDataSource.get());
        this.service = new AsyncUserService(repo);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postToKinesis(User user) {
        ApiResponse response = new ApiResponse();
        try {
            String guid = service.postToKinesis(user);
            response.add("request-id", guid);
            response.add("status-url", "/users/async/status/" + guid);
            return Response.status(ACCEPTED).entity(response).build();
        } catch (BadInputException be) {
            AppUtils.logEx(logger, response, be, "Failed to submit request due to input validation failure.");
        } catch (Exception e) {
            AppUtils.logEx(logger, response, e, "Unable to submit request due to internal server error.");
        }
        return AppUtils.buildResponse(response);
    }

    @GET
    @Path("/status/{guid}")
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserRequestStatus(@PathParam("guid") String guid) {
        ApiResponse response = new ApiResponse();
        try {
            com.codr.aws.dto.AsyncRequestStatus status = service.getStatus(guid);
            response.add("status", status.getStatus().toString().toLowerCase());
            if (status.getStatus() == AsyncRequestStatus.PROCESSED) {
                if (status.getResourceType().type() == AsyncRequestResourceType.USER.type()) {
                    String uri = String.format(status.getResourceType().uri(), status.getResourceGuid());
                    response.add("uri", uri);
                }
                return Response
                        .status(FOUND)
                        .entity(response)
                        .build();
            } else {
                return Response
                        .status(ACCEPTED)
                        .entity(response)
                        .build();
            }
        } catch (BadInputException be) {
            AppUtils.logEx(logger, response, be, "Unable to get status due to input validation failure.");
        } catch (Exception e) {
            AppUtils.logEx(logger, response, e, "Unable to get status due to internal server error.");
        }
        return AppUtils.buildResponse(response);
    }
}