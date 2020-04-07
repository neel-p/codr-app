package com.codr.aws.repo;

import com.codr.aws.dto.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo {

    String save(User user);
    Optional<List<User>> getUsers();
    Optional<User> getUserByGuid(String guid);

}
