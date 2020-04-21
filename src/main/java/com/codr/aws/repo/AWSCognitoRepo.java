package com.codr.aws.repo;

import com.amazonaws.services.cognitoidp.model.*;
import com.codr.aws.entity.LoginInformation;
import com.codr.aws.entity.PasswordRequest;
import com.codr.aws.entity.ResetPasswordRequest;
import com.codr.aws.entity.UserInformation;

import java.util.List;

public interface AWSCognitoRepo {

    /**
     * Create a user pool by pool name.
     *
     * @param userPoolName
     * @return
     * @throws Exception
     */
    CreateUserPoolResult createUserPool(String userPoolName) throws Exception;

    /**
     * Create a user pool client by app client name.
     *
     * @param userPoolClientName
     * @return
     * @throws Exception
     */
    CreateUserPoolClientResult createUserPoolClient(String userPoolClientName) throws Exception;

    /**
     * <p>
     * Create a new user.
     * </p>
     */
    void createNewUser(UserInformation userInformation) throws Exception;

    /**
     * <p>
     * Create a new user pool group.
     * </p>
     *
     * @param groupName
     * @param description
     * @param roleArn
     * @param precedence
     */
    void createNewUserGroup(String groupName, String description, String roleArn, Integer precedence) throws Exception;

    /**
     * <p>
     * Delete a user. Since deleting a user can be a "big deal" a password is required.
     * </p>
     *
     * @param userName
     * @param password
     */
    void deleteUser(String userName, String password) throws Exception;


    /**
     * Find a user by email address.
     *
     * @param email
     * @return
     * @throws Exception
     */
    UserInformation findUserByEmailAddr(final String email) throws Exception;

    /**
     * <p>
     * Update selected user attributes.
     * </p>
     *
     * @param newInfo
     * @throws AWSCognitoIdentityProviderException
     */
    void updateUserAttributes(UserInformation newInformation) throws Exception;

    /**
     * <p>
     * Log a user into the system
     * </p>
     *
     * @param userName
     * @param password
     * @return a LoginInfo object may include special context information.
     */
    LoginInformation userLogin(String userName, String password) throws Exception;

    /**
     * <p>
     * Log the user out.
     * </p>
     *
     * @param userName
     */
    void userLogout(String userName) throws Exception;

    /**
     * <p>
     * Change the user's password from a temporary password to a new (permanent) password.
     * </p>
     * @return a AdminRespondToAuthChallengeResult object may include special context information.
     */
    public AdminRespondToAuthChallengeResult changeFromTemporaryPassword(final PasswordRequest passwordRequest) throws Exception;

    /**
     * Support for resetting the user's password in the event that it was forgotten.
     *
     * @param userName
     * @param resetCode
     * @throws Exception
     */
    void forgotPassword(final String userName) throws Exception;


    /**
     * <p>
     * Get the information associated with the user.
     * </p>
     *
     * @param userName the name of the user
     * @return a UserInfo object if the information could be retrieved, null otherwise.
     */
    UserInformation getUserInformation(String userName) throws Exception; // getUserInfo

    /**
     * <p>
     * Determine whether a user with userName exists in the login database.
     * </p>
     *
     * @param userName
     * @return true if the user exists, false otherwise.
     */
    boolean hasUser(String userName);


    /**
     * <p>
     * Reset a user's password using an authentication code.
     * </p>
     *
     * @param resetRequest
     * @throws Exception
     */
    void resetPassword(ResetPasswordRequest resetRequest) throws Exception;

    /**
     * <p>
     * Change the password for a logged in user. Unlike the forgotten password logic, this does not require an
     * authentication code.
     * </p>
     *
     * @param passwordRequest
     * @throws Exception
     */
    void changePassword(PasswordRequest passwordRequest) throws Exception;

    /**
     * <p>
     * Change the email address of a logged in user.
     * </p>
     *
     * @param userName
     * @param newEmailAddr
     * @throws Exception
     */
    void changeEmail(String userName, String newEmailAddr) throws Exception;


    List<UserType> listUsers() throws Exception;


}
