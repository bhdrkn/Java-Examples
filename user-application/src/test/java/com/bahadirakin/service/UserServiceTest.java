package com.bahadirakin.service;

import com.bahadirakin.dao.UserRepository;
import com.bahadirakin.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by bhdrkn on 08/02/15.
 */
@RunWith(JUnit4.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void testAuthentication(){
        final String username = "bhdrkn";
        final String password = "1q2w3e";

        final User mockUser = new User();
        mockUser.setId("1");
        mockUser.setUsername(username);
        mockUser.setPassword(password);
        mockUser.setEmail("bhdrkn@gmail.com");

        Mockito.when(userRepository.findByUsernameAndPassword(username,password)).thenReturn(mockUser);

        final User user = userService.authenticate(username, password);
        Assert.assertNotNull(user);
    }

    @Test
    public void testUnsuccessfulAuthentication(){
        final String username = "bhdrkn";
        final String password = "1q2w3e";

        Mockito.when(userRepository.findByUsernameAndPassword(username,password)).thenReturn(null);

        final User user = userService.authenticate(username, password);
        Assert.assertNull(user);
    }

}
