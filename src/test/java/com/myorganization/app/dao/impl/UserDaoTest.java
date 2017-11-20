package com.myorganization.app.dao.impl;

import com.myorganization.app.dao.UserDao;
import com.myorganization.app.model.ERight;
import com.myorganization.app.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserDaoTest {

    private UserDao userDao = new UserDaoImpl();
    private User user;

    @Before
    public void setUp() {
        userDao.deleteAllUsers();

        final List<ERight> eRight = new ArrayList<>();
        eRight.add(ERight.POST_READ);
        user = new User("Name", eRight);
        userDao.createUser(user);
    }

    @Test
    public void testCreateUser() {
        final User storedUser = userDao.getUserById(user.getId());
        Assert.assertTrue(user.equals(storedUser));
    }

    @Test
    public void testDeleteUserById() {
        User storedUser = userDao.getUserById(user.getId());
        Assert.assertNotNull(storedUser);
        userDao.deleteUserById(user.getId());

        storedUser = userDao.getUserById(user.getId());
        Assert.assertNull(storedUser);
    }

    @Test
    public void testGetUserById() {
        final User storedUser = userDao.getUserById(user.getId());
        Assert.assertTrue(user.equals(storedUser));
    }

//    @Test
// //?getAllPosts() doesn't exists
//    public void testDeleteAllPosts() {
//        List<Post> storedPosts = postDao.getAllPosts();
//        Assert.assertTrue(!storedPosts.isEmpty());
//
//        postDao.deleteAllPosts();
//
//        storedPosts = postDao.getAllPosts();
//        Assert.assertTrue(storedPosts.isEmpty());
//    }
}
