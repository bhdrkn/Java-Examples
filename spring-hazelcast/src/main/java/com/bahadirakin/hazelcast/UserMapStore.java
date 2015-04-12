package com.bahadirakin.hazelcast;

import com.bahadirakin.hazelcast.dao.UserRepository;
import com.bahadirakin.hazelcast.domain.User;
import com.hazelcast.core.MapStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class UserMapStore implements MapStore<String, User> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void store(String key, User value) {
        userRepository.save(value);
    }

    @Override
    public void storeAll(Map<String, User> map) {
        for (Map.Entry<String, User> userEntry : map.entrySet()) {
            this.store(userEntry.getKey(), userEntry.getValue());
        }
    }

    @Override
    public void delete(String key) {
        userRepository.deleteByUsername(key);
    }

    @Override
    public void deleteAll(Collection<String> keys) {
        for (String key : keys) {
            this.delete(key);
        }
    }

    @Override
    public User load(String key) {
        return userRepository.findByUsername(key);
    }

    @Override
    public Map<String, User> loadAll(Collection<String> keys) {
        final Map<String, User> users = new HashMap<>();
        for (String key : keys) {
            users.put(key, load(key));
        }

        return users;
    }

    @Override
    public Set<String> loadAllKeys() {
        return userRepository.findAllUsernames();
    }
}
