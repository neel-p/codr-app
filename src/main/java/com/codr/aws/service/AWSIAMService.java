package com.codr.aws.service;

import com.amazonaws.services.identitymanagement.model.CreateRoleResult;
import com.codr.aws.repo.AWSIAMRepo;

public class AWSIAMService {

    private final AWSIAMRepo awsiamRepo;

    public AWSIAMService(AWSIAMRepo awsiamRepo) {
        this.awsiamRepo = awsiamRepo;
    }

    public CreateRoleResult createRole(String roleName, String description) throws Exception {
        return awsiamRepo.crateRole(roleName, description);
    }
}
