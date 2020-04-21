package com.codr.aws.controller;

import com.amazonaws.services.identitymanagement.model.CreateRoleResult;
import com.codr.aws.config.JdbiDataSource;
import com.codr.aws.dto.ApiResponse;
import com.codr.aws.exceptions.BadInputException;
import com.codr.aws.repo.AWSIAMRepo;
import com.codr.aws.repo.impl.AWSIAMRepoImpl;
import com.codr.aws.service.AWSIAMService;
import com.codr.aws.util.AppUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/iam")
public class IAMController {

    private final AWSIAMRepo awsiamRepo;
    private final AWSIAMService awsiamService;
    private static final Logger logger = LoggerFactory.getLogger(IAMController.class);

    public IAMController(AWSIAMRepo awsiamRepo, AWSIAMService awsiamService) {
        this.awsiamRepo = awsiamRepo;
        this.awsiamService = awsiamService;
    }

    public IAMController() {
        this.awsiamRepo = new AWSIAMRepoImpl(JdbiDataSource.get());
        this.awsiamService = new AWSIAMService(awsiamRepo);
    }

    @POST
    @Path("/createRole")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRole() throws JsonProcessingException {
        String roleName = "Arbitrator_Role";
        String description = "Arbitrator";
        ApiResponse response = new ApiResponse();
        try {
            CreateRoleResult createRoleResult = awsiamService.createRole(roleName, description);
            response.add("role_id", createRoleResult.getRole().getRoleId());
            response.add("role_name", createRoleResult.getRole().getRoleName());
            response.add("role_arn", createRoleResult.getRole().getArn());
            response.add("role_path", createRoleResult.getRole().getPath());
            response.add("role_last_used", createRoleResult.getRole().getRoleLastUsed());
            response.add("role_policy_document", createRoleResult.getRole().getAssumeRolePolicyDocument());
            System.out.println("Arn: " + createRoleResult.getRole().getArn() + " Path: " + createRoleResult.getRole().getPath() + " RoleID: " + createRoleResult.getRole().getRoleId()
                    + " RoleName: " + createRoleResult.getRole().getRoleName() + " LastUsed: " + createRoleResult.getRole().getRoleLastUsed() + " AssumeRolePolicyDocument: " + createRoleResult.getRole().getAssumeRolePolicyDocument());
        } catch (BadInputException be) {
            AppUtils.logEx(logger, response, be, "Failed to create role due to input validation failure.");
        } catch (Exception e) {
            AppUtils.logEx(logger, response, e, "Failed to create role at this moment.");
        }
        return AppUtils.buildResponse(response);
    }
}
