package com.bahadirakin.dynamodb.dao;

import com.bahadirakin.dynamodb.model.User;
import com.bahadirakin.dynamodb.rules.LocalDynamoDBCreationRule;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class UserRepositoryImplTest {

    @ClassRule
    public static final LocalDynamoDBCreationRule dynamoDB = new LocalDynamoDBCreationRule(User.class);

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private UserRepositoryImpl userDAO;

    @Before
    public void setUp() throws Exception {
        userDAO = new UserRepositoryImpl(dynamoDB.getDynamoDBMapper());
    }

    @Test
    public void createdUserShouldBeReadable() throws Exception {
        // Given
        final User user = new User();
        user.setUsername(UUID.randomUUID().toString());
        user.setPassword(UUID.randomUUID().toString());
        user.setEmail(UUID.randomUUID().toString());

        // When
        userDAO.put(user);
        final User actualUser = userDAO.get(user.getUsername());

        // Then
        assertThat(actualUser, is(equalTo(user)));
    }

    @Test
    public void createdUsersShouldBeDeletable() throws Exception {
        // Given
        final User user = new User();
        user.setUsername(UUID.randomUUID().toString());
        user.setPassword(UUID.randomUUID().toString());
        user.setEmail(UUID.randomUUID().toString());

        // When
        userDAO.put(user);
        userDAO.delete(user.getUsername());
        final List<User> allUsers = userDAO.findAll();

        // Then
        assertThat(allUsers, not(contains(user)));
    }

    @Test
    public void createdUsersShouldBeFindableUsingEmail() throws Exception {
        // Given
        final User user = new User();
        user.setUsername(UUID.randomUUID().toString());
        user.setPassword(UUID.randomUUID().toString());
        user.setEmail(UUID.randomUUID().toString());

        // When
        userDAO.put(user);
        final List<User> users = userDAO.findByEmail(user.getEmail());

        // Then
        assertThat(users, both(hasSize(1)).and(contains(user)));
    }

    @Test
    public void getShouldThrowExceptionWhenUserWasNotFound() throws Exception {
        // Given
        final String username = UUID.randomUUID().toString();

        // Then -- expected exception
        expectedException.expect(Exception.class);
        expectedException.expectMessage(String.format("User for username %s was not found", username));

        // When
        userDAO.get(username);
    }
}