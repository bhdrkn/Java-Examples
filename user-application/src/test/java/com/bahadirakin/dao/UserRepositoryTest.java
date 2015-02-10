package com.bahadirakin.dao;

import com.bahadirakin.dao.UserRepository;
import com.bahadirakin.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by bhdrkn on 08/02/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath*:springDataContext-test.xml"})
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    static User user;

    @Before
    public void init() {
        if(user != null){
            return;
        }
        user = new User();
        user.setUsername("bhdrkn");
        user.setEmail("bhdrkn@gmail.com");
        user.setPassword("1q2w3e");
        user = userRepository.save(user);
    }


    @Test
    public void testFindByUsernameAndPassword() {
        final User user = userRepository.findByUsernameAndPassword("bhdrkn", "1q2w3e");
        Assert.assertNotNull(user);
    }

    @Test
    public void testNotFindByUsernameAndPassword() {
        final User user = userRepository.findByUsernameAndPassword("bhdrkn", "1");
        Assert.assertNull(user);
    }

    @Test
    public void testFindByEmail(){
        final User user = userRepository.findByEmail("bhdrkn@gmail.com");
        Assert.assertNotNull(user);
    }

    @Test
    public void testNotFindByEmail(){
        final User user = userRepository.findByEmail("asd");
        Assert.assertNull(user);
    }

}
