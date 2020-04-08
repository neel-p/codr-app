package com.codr.aws.repo;

import com.codr.aws.dto.UserType;

import java.util.List;
import java.util.Optional;

public interface UserTypeRepo {

    Optional<List<UserType>> getUserTypes();
}
