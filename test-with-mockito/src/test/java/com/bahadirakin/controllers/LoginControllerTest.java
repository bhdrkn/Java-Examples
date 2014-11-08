package com.bahadirakin.controllers;

import com.bahadirakin.entities.User;
import com.bahadirakin.exceptions.UserNotFoundException;
import com.bahadirakin.services.IUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.internal.verification.Times;

/**
 * Created by bhdrkn on 08/11/14.
 */
public class LoginControllerTest {

    @Mock
    private IUserService userService;

    @InjectMocks
    private LoginController loginController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAuhtentication() throws Exception {
        final User userStub = new User();
        userStub.setUsername("bhdrkn");
        userStub.setPassword("passowrd");

        Mockito.when(userService.authenticate(userStub)).thenReturn(true);

        final String redirect = loginController.authenticate(userStub);
        Assert.assertEquals("homePage", redirect);
        Mockito.verify(userService, new Times(1)).authenticate(Mockito.any(User.class));
    }

    @Test
    public void testAuhtenticationForUserNotFound() throws Exception {
        final User userStub = new User();
        userStub.setUsername("bhdrkn");
        userStub.setPassword("passowrd");

        Mockito.when(userService.authenticate(userStub)).thenThrow(UserNotFoundException.class);

        final String redirect = loginController.authenticate(userStub);
        Assert.assertEquals(redirect, "errorPage?message=userNotFound");
        Mockito.verify(userService, new Times(1)).authenticate(Mockito.any(User.class));
    }

    @Test
    public void testAuhtenticationForWrongPassword() throws Exception {
        final User userStub = new User();
        userStub.setUsername("bhdrkn");
        userStub.setPassword("passowrd");

        Mockito.when(userService.authenticate(userStub)).thenReturn(false);

        final String redirect = loginController.authenticate(userStub);
        Assert.assertEquals(redirect, "errorPage?message=wrongPassword");
        Mockito.verify(userService, new Times(1)).authenticate(Mockito.any(User.class));
    }
}
