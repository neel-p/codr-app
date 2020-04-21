package com.codr.aws.repo;

import com.amazonaws.services.identitymanagement.model.CreateRoleResult;

public interface AWSIAMRepo {

    /**
     * <p>
     * Create a new IAM role.
     * </p>
     *
     * @param roleName
     * @param description
     * @return CreateRoleResult object
     */
    public CreateRoleResult crateRole(String roleName, String description) throws Exception;
}
