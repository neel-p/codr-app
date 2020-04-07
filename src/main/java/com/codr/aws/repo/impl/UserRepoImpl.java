package com.codr.aws.repo.impl;

import com.amazonaws.util.StringUtils;
import com.codr.aws.exceptions.UserSaveException;
import com.codr.aws.dto.User;
import com.codr.aws.repo.UserRepo;
import org.jdbi.v3.core.Jdbi;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepoImpl implements UserRepo {

    private final Jdbi jdbi;

    public UserRepoImpl(final Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public String save(User user) {
        if (StringUtils.isNullOrEmpty(user.getGuid())) {
            return insert(user);
        } else {
            return update(user);
        }
    }

    private String insert(User user) {
        return jdbi.withHandle(h -> {
            String guid = UUID.randomUUID().toString();
            user.setGuid(guid);
            int i = h.createUpdate("insert into users(contact, first_name, last_name, guid, created_on) values (:contact, :firstName, :lastName, :guid, UTC_TIMESTAMP())")
                    .bind("contact", user.getContact())
                    .bind("firstName", user.getFirstName())
                    .bind("lastName", user.getLastName())
                    .bind("guid", user.getGuid())
                    .execute();
            if (i <= 0) {
                throw new UserSaveException("Unable to save user due to internal server error.");
            }
            return guid;
        });
    }

    private String update(User user) {
        return jdbi.withHandle(h -> {
            int i = h.createUpdate("update users set contact=:contact, first_name=:firstName, last_name=:lastName, modified_on=UTC_TIMESTAMP() where guid=:guid")
                    .bind("contact", user.getContact())
                    .bind("firstName", user.getFirstName())
                    .bind("lastName", user.getLastName())
                    .bind("guid", user.getGuid())
                    .execute();
            if (i <= 0) {
                throw new UserSaveException("User doesn't exists in the system.");
            }
            return user.getGuid();
        });
    }

    @Override
    public Optional<List<User>> getUsers() {
        return jdbi.withHandle(handle -> {
            List<User> list = handle
                    .createQuery("SELECT * FROM users")
                    .map((rs, ctx) ->
                            new User(rs.getString("guid"),
                                    rs.getString("first_name"),
                                    rs.getString("last_name"),
                                    rs.getString("contact")))
                    .list();
            return Optional.ofNullable(list);
        });
    }

    @Override
    public Optional<User> getUserByGuid(String guid) {
        return jdbi.withHandle(h -> h
                .createQuery("SELECT * FROM users where guid=:guid")
                .bind("guid", guid)
                .map((rs, ctx) -> new User(rs.getString("guid"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("contact")))
                .findFirst());
    }
}