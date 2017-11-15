package com.myorganization.app.dao;

import com.myorganization.app.model.User;

public interface UserDao {

    long createUser(User user);

    User getUserById(long id);

    void deleteUserById(long id);

    void deleteAllUsers();

}
