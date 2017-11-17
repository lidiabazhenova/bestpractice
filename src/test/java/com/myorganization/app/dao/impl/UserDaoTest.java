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
    User storedUser;
    private List<ERight> eRight;

    @Before
    public void setUp() {
        userDao.deleteAllUsers();
        eRight=new ArrayList<>();
        eRight.add(ERight.POST_READ);
        user = new User("Name", eRight);
        userDao.createUser(user);
        storedUser = userDao.getUserById(user.getId());
    }
    @Test
    public void testCreateUser() {
        User storedPost = userDao.getUserById(user.getId());
        Assert.assertTrue(user.equals(storedUser));
    }

    @Test
    public void testDeleteUserById() {

        Assert.assertNotNull(storedUser);

        userDao.deleteUserById(user.getId());

        storedUser = userDao.getUserById(user.getId());;
        Assert.assertNull(storedUser);
    }

    @Test
    public void testGetUserById() {

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
