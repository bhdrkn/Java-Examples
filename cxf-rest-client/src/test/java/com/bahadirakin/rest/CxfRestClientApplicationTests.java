package com.bahadirakin.rest;

import com.bahadirakin.rest.api.User;
import com.bahadirakin.rest.api.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CxfRestClientApplication.class)
@WebAppConfiguration
public class CxfRestClientApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void testGetUsers() {
        final List<User> users = userService.findAllUsers();
        Assert.assertNotNull(users);
    }

}
