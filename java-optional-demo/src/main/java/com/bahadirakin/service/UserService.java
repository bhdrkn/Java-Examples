package com.bahadirakin.service;

import com.bahadirakin.model.User;

public interface UserService {

    String DEFAULT_COUNTRY_CODE = "TR";

    User getUser(final String username) throws UserNotFoundException;

    String getUserCountryCode(final String username);
}
