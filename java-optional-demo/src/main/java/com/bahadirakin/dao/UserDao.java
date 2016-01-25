package com.bahadirakin.dao;

import com.bahadirakin.model.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> findUser(final String username);

    @Deprecated
    User getUser(final String username);
}
