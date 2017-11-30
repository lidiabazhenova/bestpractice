package com.myorganization.app.service.impl;

import com.myorganization.app.dao.PostDao;
import com.myorganization.app.dao.UserDao;
import com.myorganization.app.exception.AccessDeniedException;
import com.myorganization.app.exception.UserException;
import com.myorganization.app.model.ERight;
import com.myorganization.app.model.Post;
import com.myorganization.app.model.User;
import com.myorganization.app.service.PostService;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class PostServiceMockTest {

    Mockery contextForInterface;

    UserDao userDaoMock;
    PostDao postDaoMock;

    PostService postService;

    @Before
    public void setUp() throws Exception {
        contextForInterface = new Mockery();

        userDaoMock = contextForInterface.mock(UserDao.class);
        postDaoMock = contextForInterface.mock(PostDao.class);

        postService = new PostServiceImpl(postDaoMock, userDaoMock);
    }

    @Test
    public void testGetPostById() throws Exception {
        final long currentUserId = 1;
        final User user = new User("Name", Arrays.asList(ERight.POST_READ));
        user.setId(currentUserId);

        final Post post = new Post("title", "content", currentUserId);
        post.setId(2);

        contextForInterface.checking(
                new Expectations() {{
                    oneOf(userDaoMock).getUserById(user.getId()); will(returnValue(user));
                    oneOf(postDaoMock).getPostById(post.getId()); will(returnValue(post));
                }});

        Post storedPost = postService.getPostById(post.getId(), currentUserId);
        Assert.assertTrue(post.equals(storedPost));
        contextForInterface.assertIsSatisfied();
    }

    @Test
    public void testGetPostByIdNoRight() throws Exception {
        final long currentUserId = 1;
        final User user = new User("Name", new ArrayList<>());
        user.setId(currentUserId);

        final Post post = new Post("title", "content", currentUserId);
        post.setId(2);

        contextForInterface.checking(
                new Expectations() {{
                    oneOf(userDaoMock).getUserById(user.getId()); will(returnValue(user));
                }});

        try {
            postService.getPostById(post.getId(), currentUserId);
            Assert.fail();
        } catch(final AccessDeniedException ex) {
            Assert.assertTrue("User don't have right to read posts".equals(ex.getMessage()));
            contextForInterface.assertIsSatisfied();
        }
    }

    @Test
    public void testCreatePost() throws Exception {
        final long currentUserId = 1;
        final User user = new User("Name", Arrays.asList(ERight.POST_WRITE));
        user.setId(currentUserId);

        final Post post = new Post("title", "content", currentUserId);
        post.setId(2);

        contextForInterface.checking(
                new Expectations() {{
                    oneOf(userDaoMock).getUserById(user.getId()); will(returnValue(user));
                    oneOf(postDaoMock).createPost(post); will(returnValue(post.getId()));
                }});

        long storedPostId = postService.createPost(post, currentUserId);
        Assert.assertEquals(post.getId(), storedPostId);
        contextForInterface.assertIsSatisfied();
    }

    @Test
    public void testCreatePostNoRights() throws Exception {
        final long currentUserId = 1;
        final User user = new User("Name", Arrays.asList(ERight.POST_READ));
        user.setId(currentUserId);

        final Post post = new Post("title", "content", currentUserId);
        post.setId(2);

        contextForInterface.checking(
                new Expectations() {{
                    oneOf(userDaoMock).getUserById(user.getId()); will(returnValue(user));
                }});

        try {
            postService.createPost(post, currentUserId);
            Assert.fail();
         } catch(final AccessDeniedException ex) {
            Assert.assertTrue("User don't have right to write posts".equals(ex.getMessage()));
            contextForInterface.assertIsSatisfied();
        }
    }

    @Test
    public void testCreatePostTitleIsEmpty() throws Exception {
        final long currentUserId = 1;
        final User user = new User("Name", Arrays.asList(ERight.POST_WRITE));
        user.setId(currentUserId);

        final Post post = new Post("", "content", currentUserId);
        post.setId(2);

        contextForInterface.checking(
                new Expectations() {{
                    oneOf(userDaoMock).getUserById(user.getId()); will(returnValue(user));
                }});

        try {
            postService.createPost(post, currentUserId);
            Assert.fail();
        } catch(final UserException ex) {
            Assert.assertTrue("Post title is empty".equals(ex.getMessage()));
            contextForInterface.assertIsSatisfied();
        }
    }

    @Test
    public void testCreatePostContentIsEmpty() throws Exception {
        final long currentUserId = 1;
        final User user = new User("Name", Arrays.asList(ERight.POST_WRITE));
        user.setId(currentUserId);

        final Post post = new Post("title", "", currentUserId);
        post.setId(2);

        contextForInterface.checking(
                new Expectations() {{
                    oneOf(userDaoMock).getUserById(user.getId()); will(returnValue(user));
                }});

        try {
            postService.createPost(post, currentUserId);
            Assert.fail();
        } catch(final UserException ex) {
            Assert.assertTrue("Post content is empty".equals(ex.getMessage()));
            contextForInterface.assertIsSatisfied();
        }
    }

    @Test
    public void testRemovePostById() throws Exception {
        final long currentUserId = 1;
        final User user = new User("Name", Arrays.asList(ERight.POST_READ));
        user.setId(currentUserId);

        final Post post = new Post("title", "content", currentUserId);
        post.setId(2);

        contextForInterface.checking(
                new Expectations() {{
                    oneOf(userDaoMock).getUserById(user.getId()); will(returnValue(user));
                    oneOf(postDaoMock).getPostById(post.getId()); will(returnValue(post));
                    oneOf(postDaoMock).deletePostById(post.getId());
                }});

        postService.removePostById(post.getId(), currentUserId);
        contextForInterface.assertIsSatisfied();
    }

    @Test
    public void testRemovePostByIdAdmin() throws Exception {
        final long currentUserId = 1;
        final User user = new User("Name", Arrays.asList(ERight.POST_ADMIN));
        user.setId(currentUserId);

        final Post post = new Post("title", "content", 2);
        post.setId(2);

        contextForInterface.checking(
                new Expectations() {{
                    oneOf(userDaoMock).getUserById(user.getId()); will(returnValue(user));
                    oneOf(postDaoMock).getPostById(post.getId()); will(returnValue(post));
                    oneOf(postDaoMock).deletePostById(post.getId());
                }});

        postService.removePostById(post.getId(), currentUserId);
        contextForInterface.assertIsSatisfied();
    }

    @Test
    public void testRemovePostByIdNoRights() throws Exception {
        final long currentUserId = 1;
        final User user = new User("Name", Arrays.asList(ERight.POST_WRITE));
        user.setId(currentUserId);

        final Post post = new Post("title", "content", 2);
        post.setId(2);

        contextForInterface.checking(
                new Expectations() {{
                    oneOf(userDaoMock).getUserById(user.getId()); will(returnValue(user));
                    oneOf(postDaoMock).getPostById(post.getId()); will(returnValue(post));
                }});

        try {
            postService.removePostById(post.getId(), currentUserId);
            Assert.fail();
        } catch(final AccessDeniedException ex) {
            Assert.assertTrue("User don't have right to delete posts".equals(ex.getMessage()));
            contextForInterface.assertIsSatisfied();
        }
    }
}
