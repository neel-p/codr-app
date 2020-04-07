package com.codr.aws.service;

import com.amazonaws.util.StringUtils;
import com.codr.aws.exceptions.UserNotFoundException;
import com.codr.aws.dto.User;
import com.codr.aws.exceptions.BadInputException;
import com.codr.aws.repo.UserRepo;
import com.codr.aws.util.AppUtils;

import java.util.List;

public class UserService {

    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public String save(User user) throws BadInputException {
        AppUtils.validate(user);
        return repo.save(user);
    }

    public List<User> getUsers() {
        return repo
                .getUsers()
                .orElseThrow(() -> new UserNotFoundException("No user found in the system"));
    }

    public User getUserByGuid(String guid) {
        if(StringUtils.isNullOrEmpty(guid)) {
            throw new BadInputException("guid is mandatory");
        }
        return repo
                .getUserByGuid(guid)
                .orElseThrow(() -> new UserNotFoundException("No user found in the system"));
    }
}
