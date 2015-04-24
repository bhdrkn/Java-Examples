package com.bahadirakin.rest.api;

import javax.ws.rs.*;
import java.util.List;

@Path("/users")
public interface UserService {

    @GET
    List<User> findAllUsers();

    @GET
    @Path("/{id}")
    User findUser(@PathParam("id") String userId);

    @POST
    User createUser(User user);
}
