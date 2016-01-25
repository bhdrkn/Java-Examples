package com.bahadirakin.service.impl;

import com.bahadirakin.dao.UserDao;
import com.bahadirakin.model.Address;
import com.bahadirakin.model.Country;
import com.bahadirakin.model.User;
import com.bahadirakin.service.UserNotFoundException;
import com.bahadirakin.service.UserService;

import static java.util.Objects.requireNonNull;

public class BetterUserServiceImpl implements UserService {

    private final UserDao userDao;

    public BetterUserServiceImpl(UserDao userDao) {
        this.userDao = requireNonNull(userDao);
    }


    @Override
    public User getUser(String username) throws UserNotFoundException {
        return userDao.findUser(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    public String getUserCountryCode(String username) {
        // Just like Groovy's "?."
        return userDao.findUser(username)
                .map(User::getAddress)
                .map(Address::getCountry)
                .map(Country::getCode)
                .orElse(DEFAULT_COUNTRY_CODE);
    }
}
