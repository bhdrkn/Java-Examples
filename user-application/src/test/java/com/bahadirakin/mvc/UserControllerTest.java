package com.bahadirakin.mvc;

import com.bahadirakin.model.User;
import com.bahadirakin.service.UserService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhdrkn on 08/02/15.
 */
@RunWith(JUnit4.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testFindAll() throws Exception{
        final User user1 = new User();
        user1.setId("1");
        user1.setEmail("bhdrkb@gmail.com");
        user1.setUsername("bhdrkn");
        user1.setPassword("1q2w3e");

        final User user2 = new User();
        user2.setId("2");
        user2.setEmail("bhdrkb2@gmail.com");
        user2.setUsername("bhdrkn2");
        user2.setPassword("1q2w3e");
        final List<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);

        Mockito.when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/users"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].username",
                        Matchers.hasItems(
                                Matchers.endsWith("bhdrkn"),
                                Matchers.endsWith("bhdrkn2"))))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void  testCreateUser() throws Exception {
        final User user = new User();
        user.setId("1");
        user.setEmail("bhdrkn@gmail.com");
        user.setUsername("bhdrkn");
        user.setPassword("1q2w3e");

        Mockito.when(userService.createUser(Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/users")
                .content("{\"username\":\"bhdrkn\",\"password\":\"1q2w3e\",\"email\":\"bhdrkn@gmail.com\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("bhdrkn")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password",Matchers.is("1q2w3e")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is("bhdrkn@gmail.com")))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGetUser() throws Exception{
        final User user = new User();
        user.setId("1");
        user.setEmail("bhdrkn@gmail.com");
        user.setUsername("bhdrkn");
        user.setPassword("1q2w3e");

        Mockito.when(userService.findUserById("1")).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/users/1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("bhdrkn")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password",Matchers.is("1q2w3e")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is("bhdrkn@gmail.com")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testNotExistedUser() throws Exception{
        Mockito.when(userService.findUserById("1")).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/users/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void  testUpdateUser() throws Exception {
        final User user = new User();
        user.setId("1");
        user.setEmail("bhdrkn@gmail.com");
        user.setUsername("bhdrkn");
        user.setPassword("1q2w3e");

        Mockito.when(userService.updateUser(Mockito.eq("1"), Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.put("/rest/users/1")
                .content("{\"username\":\"bhdrkn\",\"password\":\"1q2w3e\",\"email\":\"bhdrkn@gmail.com\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("bhdrkn")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password",Matchers.is("1q2w3e")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is("bhdrkn@gmail.com")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void  testDeleteUser() throws Exception {
        final User user = new User();
        user.setId("1");
        user.setEmail("bhdrkn@gmail.com");
        user.setUsername("bhdrkn");
        user.setPassword("1q2w3e");

        Mockito.when(userService.deleteUser(Mockito.eq("1"),Mockito.any(User.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/users/1")
                .content("{\"username\":\"bhdrkn\",\"password\":\"1q2w3e\",\"email\":\"bhdrkn@gmail.com\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("bhdrkn")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password",Matchers.is("1q2w3e")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email",Matchers.is("bhdrkn@gmail.com")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
