package com.codr.aws.service;

import com.codr.aws.dto.User;
import com.codr.aws.dto.UserType;
import com.codr.aws.exceptions.BadInputException;
import com.codr.aws.exceptions.UserNotFoundException;
import com.codr.aws.repo.UserRepo;
import com.codr.aws.repo.UserTypeRepo;
import com.codr.aws.util.AppUtils;

import java.util.List;

public class UserTypeService {

    private final UserTypeRepo userTypeRepo;

    public UserTypeService(UserTypeRepo userTypeRepo) {
        this.userTypeRepo = userTypeRepo;
    }

    public List<UserType> getUserTypes() {
        return userTypeRepo
                .getUserTypes()
                .orElseThrow(() -> new UserNotFoundException("No user type found in the system"));
    }
}
