package com.codr.aws.repo.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClientBuilder;
import com.amazonaws.services.identitymanagement.model.CreateRoleRequest;
import com.amazonaws.services.identitymanagement.model.CreateRoleResult;
import com.codr.aws.repo.AWSIAMRepo;
import com.codr.aws.resource.CognitoResources;
import org.jdbi.v3.core.Jdbi;

public class AWSIAMRepoImpl implements AWSIAMRepo, CognitoResources {

    protected static AmazonIdentityManagement iam = null;
    private final Jdbi jdbi;

    public AWSIAMRepoImpl(Jdbi jdbi) {
        this.jdbi = jdbi;
        if (iam == null) {
            iam = getAmazonIdentityManagement();
        }
    }

    /**
     * <p>
     * Build an AWSCredentials object from the ID and secret key.
     * </p>
     *
     * @param AWS_ID
     * @param AWS_KEY
     * @return an AWSCredentials object, initialized with the ID and Key.
     */
    protected AWSCredentials getCredentials(String AWS_ID, String AWS_KEY) {
        AWSCredentials credentials = new BasicAWSCredentials(AWS_ID, AWS_KEY);
        return credentials;
    }

    /**
     * <p>
     * Build an Amazon identity provider, based on the parameters defined in the CognitoResources interface.
     * </p>
     *
     * @return
     */
    protected AmazonIdentityManagement getAmazonIdentityManagement() {
        AWSCredentials credentials = getCredentials(cognitoID, cognitoKey);
        AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(credentials);
        AmazonIdentityManagement client = AmazonIdentityManagementClientBuilder.standard().withCredentials(credProvider).withRegion(region).build();
        return client;
    }

    /**
     * <p>
     * Create a new IAM role.
     * </p>
     *
     * @param roleName
     * @param description
     * @return CreateRoleResult object
     */
    public CreateRoleResult crateRole(String roleName, String description) {
        String policyDocument = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"Service\":[\"lambda.amazonaws.com\"]},\"Action\":[\"sts:AssumeRole\"]}]}";
        CreateRoleRequest createRoleRequest = new CreateRoleRequest()
                .withRoleName(roleName)
                .withDescription(description)
                .withAssumeRolePolicyDocument(policyDocument);
        CreateRoleResult createRoleResult = iam.createRole(createRoleRequest);
        return createRoleResult;
    }
}
