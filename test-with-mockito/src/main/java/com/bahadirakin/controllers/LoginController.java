package com.bahadirakin.controllers;

import com.bahadirakin.entities.User;
import com.bahadirakin.exceptions.UserNotFoundException;
import com.bahadirakin.services.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created by bhdrkn on 08/11/14.
 */
public class LoginController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private IUserService userService;

    public LoginController(IUserService userService) {
        this.userService = userService;
    }

    public String authenticate(final User user) {

        try {
            if (userService.authenticate(user)) {
                return "homePage";
            } else {
                return "errorPage?message=wrongPassword";
            }
        } catch (UserNotFoundException e) {
            logger.error("User not found for usernmae: {}", user.getUsername(), e);
            return "errorPage?message=userNotFound";
        }
    }

}
