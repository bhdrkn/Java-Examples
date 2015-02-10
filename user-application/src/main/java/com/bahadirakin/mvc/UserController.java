package com.bahadirakin.mvc;

import com.bahadirakin.model.User;
import com.bahadirakin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bhdrkn on 08/02/15.
 */
@Controller
@RequestMapping("/rest/users")
public class UserController implements Serializable{

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<User>> findAll(){
        final List<User> users = userService.getAllUsers();

        if(users == null){
            return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user){
        try {
            final User dbuser = userService.createUser(user);
            return new ResponseEntity<User>(dbuser, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/{id}",method =  RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable String id){
        try {
            final User dbuser = userService.findUserById(id);
            if(dbuser == null){
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<User>(dbuser, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/{id}",method =  RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user){
        try {
            final User dbuser = userService.updateUser(id, user);
            return new ResponseEntity<User>(dbuser, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value="/{id}",method =  RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable String id, @RequestBody User user){
        try {
            final User dbuser = userService.deleteUser(id, user);
            return new ResponseEntity<User>(dbuser, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
        } catch (Exception e){
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }
}
