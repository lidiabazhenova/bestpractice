package com.myorganization.app.dao.impl;

import com.myorganization.app.dao.UserDao;
import com.myorganization.app.model.Post;
import com.myorganization.app.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private static final Map<Long, User> users = new HashMap<>();

    @Override
    public long createUser(User user) {
        long id = System.currentTimeMillis();
        user.setId(id);

        users.put(id, user);

        return id;
    }

    @Override
    public User getUserById(long id) {
        return users.get(id);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void deleteUserById(long id) {
        users.remove(id);
    }

    @Override
    public void deleteAllUsers() {
        users.clear();
    }
}
