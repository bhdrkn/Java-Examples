package com.bahadirakin.dynamodb.dao;

import com.bahadirakin.dynamodb.model.User;

import java.util.List;

public interface UserRepository {

    void put(User user);

    void delete(String username);

    User get(String username) throws Exception;

    List<User> findAll();

    List<User> findByEmail(final String email);
}
