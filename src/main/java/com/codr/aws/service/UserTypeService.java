package com.codr.aws.service;

import com.codr.aws.dto.UserType;
import com.codr.aws.exceptions.UserTypeNotFoundException;
import com.codr.aws.repo.UserTypeRepo;

import java.util.List;

public class UserTypeService {

    private final UserTypeRepo userTypeRepo;

    public UserTypeService(UserTypeRepo userTypeRepo) {
        this.userTypeRepo = userTypeRepo;
    }

    public List<UserType> getUserTypes() {
        return userTypeRepo
                .getUserTypes()
                .orElseThrow(() -> new UserTypeNotFoundException("No user type found in the system"));
    }
}
