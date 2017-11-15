package com.myorganization.app.dao.impl;


import com.myorganization.app.dao.PostDao;
import com.myorganization.app.model.Post;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PostDaoTest {

    private PostDao postDao = new PostDaoImpl();
    private Post post;

    @Before
    public void setUp() {
        postDao.deleteAllPosts();

        post = new Post("title", "content", 1);
        postDao.createPost(post);
    }

    @Test
    public void testCreatePost() {
        Post storedPost = postDao.getPostById(post.getId());
        Assert.assertTrue(post.equals(storedPost));
    }

    @Test
    public void testDeletePostById() {
        Post storedPost = postDao.getPostById(post.getId());
        Assert.assertNotNull(storedPost);

        postDao.deletePostById(post.getId());

        storedPost = postDao.getPostById(post.getId());
        Assert.assertNull(storedPost);
    }

    @Test
    public void testGetPostById() {
        Post storedPost = postDao.getPostById(post.getId());
        Assert.assertTrue(post.equals(storedPost));
    }

    @Test
    public void testDeleteAllPosts() {
        List<Post> storedPosts = postDao.getAllPosts();
        Assert.assertTrue(!storedPosts.isEmpty());

        postDao.deleteAllPosts();

        storedPosts = postDao.getAllPosts();
        Assert.assertTrue(storedPosts.isEmpty());
    }

}
