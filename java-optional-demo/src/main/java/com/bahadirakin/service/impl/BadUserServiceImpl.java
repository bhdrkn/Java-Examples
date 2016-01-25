package com.bahadirakin.service.impl;

import com.bahadirakin.dao.UserDao;
import com.bahadirakin.model.User;
import com.bahadirakin.service.UserNotFoundException;
import com.bahadirakin.service.UserService;

import static java.util.Objects.requireNonNull;

public class BadUserServiceImpl implements UserService {

    private final UserDao userDao;

    public BadUserServiceImpl(UserDao userDao) {
        this.userDao = requireNonNull(userDao);
    }


    @Override
    public User getUser(String username) throws UserNotFoundException {

        final User user = userDao.getUser(username);

        if (user == null) {
            throw new UserNotFoundException(username);
        }

        return user;
    }

    @Override
    public String getUserCountryCode(String username) {

        final User user = userDao.getUser(username);

        // WTF??
        if (user == null
                || user.getAddress() == null
                || user.getAddress().getCountry() == null
                || user.getAddress().getCountry().getCode() == null) {
            return DEFAULT_COUNTRY_CODE;
        }

        return user.getAddress().getCountry().getCode();
    }
}
