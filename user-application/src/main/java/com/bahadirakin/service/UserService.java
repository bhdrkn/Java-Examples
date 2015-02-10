package com.bahadirakin.service;

import com.bahadirakin.model.User;

import java.util.List;

/**
 * Created by bhdrkn on 08/02/15.
 */
public interface UserService {

    User createUser(final User user);

    User updateUser(String id, final User user);

    User deleteUser(String id, final User user);

    User findUserById(final String id);

    List<User> getAllUsers();

    User authenticate(final String username, final String password);

    void sendForgottenUsernameAndPasswordEmail(final String email);
}
