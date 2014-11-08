package com.bahadirakin.services;

import com.bahadirakin.entities.User;
import com.bahadirakin.exceptions.UserNotFoundException;

/**
 * Created by bhdrkn on 08/11/14.
 */
public interface IUserService {

    boolean authenticate(final User user) throws UserNotFoundException;

}
