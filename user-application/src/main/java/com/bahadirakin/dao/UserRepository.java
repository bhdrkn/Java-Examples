package com.bahadirakin.dao;

import com.bahadirakin.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by bhdrkn on 08/02/15.
 */
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(final String email);
    User findByUsernameAndPassword(final String username, final String password);
}
