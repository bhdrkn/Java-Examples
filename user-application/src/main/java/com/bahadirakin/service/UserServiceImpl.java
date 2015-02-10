package com.bahadirakin.service;

import com.bahadirakin.dao.UserRepository;
import com.bahadirakin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bhdrkn on 08/02/15.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String id, User user) {
        final User dbUser = userRepository.findOne(id);
        dbUser.setUsername(user.getUsername());
        dbUser.setPassword(user.getPassword());
        dbUser.setEmail(user.getEmail());
        return userRepository.save(dbUser);
    }

    @Override
    public User deleteUser(String id, User user) {
        final User dbuser = this.authenticate(user.getUsername(), user.getPassword());
        if(dbuser== null || dbuser.getId().equals(id) == false){
            throw new RuntimeException("Wrong User");
        }
        userRepository.delete(user.getId());
        return user;
    }

    @Override
    public User findUserById(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User authenticate(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public void sendForgottenUsernameAndPasswordEmail(String email) {
        final User user = userRepository.findByEmail(email);
        // Sends email etc.
    }
}
