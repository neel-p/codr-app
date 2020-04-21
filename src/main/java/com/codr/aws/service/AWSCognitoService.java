package com.codr.aws.service;

import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeResult;
import com.amazonaws.services.cognitoidp.model.CreateUserPoolClientResult;
import com.amazonaws.services.cognitoidp.model.CreateUserPoolResult;
import com.amazonaws.services.cognitoidp.model.UserType;
import com.codr.aws.entity.LoginInformation;
import com.codr.aws.entity.PasswordRequest;
import com.codr.aws.entity.UserInformation;
import com.codr.aws.repo.AWSCognitoRepo;

import java.util.List;

public class AWSCognitoService {

    private final AWSCognitoRepo awsCognitoRepo;

    public AWSCognitoService(AWSCognitoRepo awsCognitoRepo) {
        this.awsCognitoRepo = awsCognitoRepo;
    }

    public CreateUserPoolResult createUserPool(String userPoolName) throws Exception {
        return awsCognitoRepo.createUserPool(userPoolName);
        /*.orElseThrow(() -> new UserTypeNotFoundException("No user type found in the system"));*/
    }

    public CreateUserPoolClientResult createUserPoolClient(String userPoolClientName) throws Exception {
        return awsCognitoRepo.createUserPoolClient(userPoolClientName);
    }

    public void createNewUser(UserInformation userInformation) throws Exception {
        awsCognitoRepo.createNewUser(userInformation);
    }

    public void createNewUserGroup(String groupName, String description, String roleArn, Integer precedence) throws Exception {
        awsCognitoRepo.createNewUserGroup(groupName, description, roleArn, precedence);
    }

    public LoginInformation userLogin(String userName, String password) throws Exception {
        return awsCognitoRepo.userLogin(userName, password);
    }

    public AdminRespondToAuthChallengeResult changeFromTemporaryPassword(PasswordRequest passwordRequest) throws Exception {
        return awsCognitoRepo.changeFromTemporaryPassword(passwordRequest);
    }

    public List<UserType> listUsers() throws Exception {
        return awsCognitoRepo.listUsers();
    }
}
