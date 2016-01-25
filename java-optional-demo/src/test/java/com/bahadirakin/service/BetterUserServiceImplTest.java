package com.bahadirakin.service;

import com.bahadirakin.dao.UserDao;
import com.bahadirakin.service.impl.BetterUserServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BetterUserServiceImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private UserDao userDao;

    @InjectMocks
    private BetterUserServiceImpl userService;

    @Test
    public void testGetUser() throws Exception {
        // given
        final String testUsername = "bahadirakin";
        when(userDao.findUser(testUsername)).thenReturn(Optional.empty());

        // then - Exception excepted
        expectedException.expect(UserNotFoundException.class);
        expectedException.expectMessage("User for bahadirakin username was not found");

        // when
        userService.getUser(testUsername);
    }

    @Test
    public void testGetUserCountryCode() throws Exception {
        // given
        final String testUsername = "bahadirakin";
        when(userDao.findUser(testUsername)).thenReturn(Optional.empty());

        // when
        final String userCountryCode = userService.getUserCountryCode(testUsername);

        // then
        verify(userDao).findUser(testUsername);
        assertThat(userCountryCode, is(UserService.DEFAULT_COUNTRY_CODE));
    }
}