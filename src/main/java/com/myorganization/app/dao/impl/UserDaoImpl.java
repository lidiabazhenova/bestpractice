package com.myorganization.app.dao.impl;

import com.myorganization.app.dao.UserDao;
import com.myorganization.app.model.ERight;
import com.myorganization.app.model.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private static final Map<Long, User> users = new HashMap<>();

    public UserDaoImpl() {
        final User user1 = new User(1, "User read only", Arrays.asList(ERight.POST_READ));
        users.put(user1.getId(), user1);

        final User user2 = new User(2, "User read write", Arrays.asList(ERight.POST_READ, ERight.POST_WRITE));
        users.put(user2.getId(), user2);

        final User user3 = new User(3, "User admin", Arrays.asList(ERight.POST_ADMIN));
        users.put(user3.getId(), user3);
    }

    public User getUserById(long id) {
        return users.get(id);
    }
}
