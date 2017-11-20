package com.myorganization.app.dao;

import com.myorganization.app.model.User;

import java.util.List;

public interface UserDao {

    long createUser(User user);

    User getUserById(long id);

    List<User> getAllUsers();

    void deleteUserById(long id);

    void deleteAllUsers();

}
