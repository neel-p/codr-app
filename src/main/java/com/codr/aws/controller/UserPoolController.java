package com.codr.aws.controller;

import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeResult;
import com.amazonaws.services.cognitoidp.model.CreateUserPoolClientResult;
import com.amazonaws.services.cognitoidp.model.CreateUserPoolResult;
import com.amazonaws.services.cognitoidp.model.UserType;
import com.codr.aws.config.JdbiDataSource;
import com.codr.aws.dto.ApiResponse;
import com.codr.aws.entity.LoginInformation;
import com.codr.aws.entity.PasswordRequest;
import com.codr.aws.entity.UserInformation;
import com.codr.aws.exceptions.BadInputException;
import com.codr.aws.repo.AWSCognitoRepo;
import com.codr.aws.repo.impl.AWSCognitoRepoImpl;
import com.codr.aws.service.AWSCognitoService;
import com.codr.aws.util.AppUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/userPool")
public class UserPoolController {

    private final AWSCognitoRepo awsCognitoRepo;
    private final AWSCognitoService awsCognitoService;
    private static final Logger logger = LoggerFactory.getLogger(UserPoolController.class);

    public UserPoolController(AWSCognitoRepo repo, AWSCognitoService service) {
        this.awsCognitoRepo = repo;
        this.awsCognitoService = service;
    }

    public UserPoolController() {
        this.awsCognitoRepo = new AWSCognitoRepoImpl(JdbiDataSource.get());
        this.awsCognitoService = new AWSCognitoService(awsCognitoRepo);
    }

    @POST
    @Path("/createUserPool/{pool_name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUserPool(@PathParam("pool_name") String poolName) throws JsonProcessingException {
        ApiResponse response = new ApiResponse();
        try {
            CreateUserPoolResult createUserPoolResult = awsCognitoService.createUserPool(poolName);
            response.add("pool_id", createUserPoolResult.getUserPool().getId());
            response.add("pool_name", createUserPoolResult.getUserPool().getName());
            response.add("pool_arn", createUserPoolResult.getUserPool().getArn());
            System.out.println("User Pool Name::: " + createUserPoolResult.getUserPool().getName() + " User Pool ID::: " + createUserPoolResult.getUserPool().getId() + " User Pool ARN::: " + createUserPoolResult.getUserPool().getArn());
        } catch (BadInputException be) {
            AppUtils.logEx(logger, response, be, "Failed to create user pool due to input validation failure.");
        } catch (Exception e) {
            AppUtils.logEx(logger, response, e, "Failed to create user pool at this moment.");
        }
        return AppUtils.buildResponse(response);
    }

    @POST
    @Path("/createUserPoolClient/{client_name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUserPoolClient(@PathParam("client_name") String clientName) throws JsonProcessingException {
        ApiResponse response = new ApiResponse();
        try {
            CreateUserPoolClientResult createUserPoolClientResult = awsCognitoService.createUserPoolClient(clientName);
            response.add("pool_id", createUserPoolClientResult.getUserPoolClient().getUserPoolId());
            response.add("client_id", createUserPoolClientResult.getUserPoolClient().getClientId());
            response.add("client_name", createUserPoolClientResult.getUserPoolClient().getClientName());
            response.add("client_secret_key", createUserPoolClientResult.getUserPoolClient().getClientSecret());
        } catch (BadInputException be) {
            AppUtils.logEx(logger, response, be, "Failed to create user pool client due to input validation failure.");
        } catch (Exception e) {
            AppUtils.logEx(logger, response, e, "Failed to create user pool client at this moment.");
        }
        return AppUtils.buildResponse(response);
    }

    @POST
    @Path("/createNewUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewUser() throws JsonProcessingException {
        UserInformation userInformation = new UserInformation("pocneel", "nilesh.pal@green-apex.com", "US_WEST_2");
        ApiResponse response = new ApiResponse();
        try {
            awsCognitoService.createNewUser(userInformation);
            response.add("OK", "Successfully added new user in user pool.");
            //response.add("pool_id", createUserPoolClientResult.getUserPoolClient().getUserPoolId());
            //response.add("client_id", createUserPoolClientResult.getUserPoolClient().getClientId());
            //response.add("client_name", createUserPoolClientResult.getUserPoolClient().getClientName());
            //response.add("client_secret_key", createUserPoolClientResult.getUserPoolClient().getClientSecret());
        } catch (BadInputException be) {
            AppUtils.logEx(logger, response, be, "Failed to create new user in user pool due to input validation failure.");
        } catch (Exception e) {
            AppUtils.logEx(logger, response, e, "Failed to create new user in user pool at this moment.");
        }
        return AppUtils.buildResponse(response);
    }

    @POST
    @Path("/createNewUserGroup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewUserGroup() throws JsonProcessingException {
        String groupName = "poc_group";
        String description = "poc_group for CODR";
        String roleArn = "arn:aws:iam::947459320907:role/Arbitrator_Role";
        Integer precedence = 100;
        ApiResponse response = new ApiResponse();
        try {
            awsCognitoService.createNewUserGroup(groupName, description, roleArn, precedence);
            response.add("OK", "Successfully added new user group.");
            //response.add("pool_id", createUserPoolClientResult.getUserPoolClient().getUserPoolId());
            //response.add("client_id", createUserPoolClientResult.getUserPoolClient().getClientId());
            //response.add("client_name", createUserPoolClientResult.getUserPoolClient().getClientName());
            //response.add("client_secret_key", createUserPoolClientResult.getUserPoolClient().getClientSecret());
        } catch (BadInputException be) {
            AppUtils.logEx(logger, response, be, "Failed to create user group in user pool due to input validation failure.");
        } catch (Exception e) {
            AppUtils.logEx(logger, response, e, "Failed to create user group in user pool at this moment.");
        }
        return AppUtils.buildResponse(response);
    }

    @POST
    @Path("/userLogin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response userLogin() throws JsonProcessingException {
        String username = "pocneel";
        String password = "D3sxZ3#q";
        ApiResponse response = new ApiResponse();
        try {
            LoginInformation loginInformation = awsCognitoService.userLogin(username, password);
            response.add("loginInformation", loginInformation);
            System.out.println("getAccessToken::: " + loginInformation.getAccessToken());
        } catch (BadInputException be) {
            AppUtils.logEx(logger, response, be, "Failed to logged in user pool due to input validation failure.");
        } catch (Exception e) {
            AppUtils.logEx(logger, response, e, "Failed to logged in user pool at this moment.");
        }
        return AppUtils.buildResponse(response);
    }


    @POST
    @Path("/changeFromTemporaryPassword")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeFromTemporaryPassword() throws JsonProcessingException {
        PasswordRequest passwordRequest = new PasswordRequest("pocneel", "D3sxZ3#q", "Green123$");
        ApiResponse response = new ApiResponse();
        try {
            AdminRespondToAuthChallengeResult adminRespondToAuthChallengeResult = awsCognitoService.changeFromTemporaryPassword(passwordRequest);
            if (adminRespondToAuthChallengeResult != null) {
                response.add("access-token", adminRespondToAuthChallengeResult.getAuthenticationResult().getAccessToken());
                response.add("expires_in", adminRespondToAuthChallengeResult.getAuthenticationResult().getExpiresIn());
                response.add("id_token", adminRespondToAuthChallengeResult.getAuthenticationResult().getIdToken());
                response.add("refresh_token", adminRespondToAuthChallengeResult.getAuthenticationResult().getRefreshToken());
                response.add("token_type", adminRespondToAuthChallengeResult.getAuthenticationResult().getTokenType());
            } else {
                System.out.print("adminRespondToAuthChallengeResult is null...");
                response.add("adminRespondToAuthChallengeResult is null...", adminRespondToAuthChallengeResult);
            }
        } catch (BadInputException be) {
            AppUtils.logEx(logger, response, be, "Failed to logged in user pool due to input validation failure.");
        } catch (Exception e) {
            AppUtils.logEx(logger, response, e, "Failed to logged in user pool at this moment.");
        }
        return AppUtils.buildResponse(response);
    }

    @GET
    @Path("/listUsers")
    @Consumes(MediaType.WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUsers() throws JsonProcessingException {
        ApiResponse response = new ApiResponse();
        try {
            List<UserType> listUsers = awsCognitoService.listUsers();
            for (int i = 0; i < listUsers.size(); i++) {
                response.add("user " + i, listUsers.get(i));
            }
            response.add("users_size", listUsers.size());
            //response.add("username", listUsers.size() > 0 ? listUsers.get(0).getUsername() : "");
        } catch (Exception e) {
            AppUtils.logEx(logger, response, e, "Unable to retrieve users list at this moment.");
        }
        return AppUtils.buildResponse(response);
    }

}
