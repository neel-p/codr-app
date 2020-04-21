package com.codr.aws.repo.impl;

import com.codr.aws.dto.UserType;
import com.codr.aws.repo.UserTypeRepo;
import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.Optional;

public class UserTypeRepoImpl implements UserTypeRepo {

    private final Jdbi jdbi;

    public UserTypeRepoImpl(final Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Optional<List<UserType>> getUserTypes() {
        return jdbi.withHandle(handle -> {
            List<UserType> list = handle
                    .createQuery("SELECT * FROM user_type")
                    .map((rs, ctx) -> new UserType(rs.getString("guid"),
                            rs.getString("user_type_name"),
                            rs.getString("user_type_details"))).list();
            return Optional.ofNullable(list);
        });
    }

}
