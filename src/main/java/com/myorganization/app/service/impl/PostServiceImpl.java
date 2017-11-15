package com.myorganization.app.service.impl;

import com.myorganization.app.dao.PostDao;
import com.myorganization.app.dao.UserDao;
import com.myorganization.app.exception.AccessDeniedException;
import com.myorganization.app.exception.UserException;
import com.myorganization.app.model.ERight;
import com.myorganization.app.model.Post;
import com.myorganization.app.model.User;
import com.myorganization.app.service.PostService;
import org.apache.commons.lang3.StringUtils;

public class PostServiceImpl implements PostService {

    private PostDao postDao;
    private UserDao userDao;

    public PostServiceImpl(PostDao postDao, UserDao userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @Override
    public Post getPostById(long id, long currentUserId) throws UserException, AccessDeniedException {
        User user = userDao.getUserById(currentUserId);
        if (!user.getRights().contains(ERight.POST_READ)) {
            throw new AccessDeniedException("User don't have right to read posts");
        }

        return postDao.getPostById(id);
    }

    @Override
    public long createPost(Post post, long currentUserId) throws UserException, AccessDeniedException {
        User user = userDao.getUserById(currentUserId);
        if (!user.getRights().contains(ERight.POST_WRITE)) {
            throw new AccessDeniedException("User don't have right to write posts");
        }

        if (StringUtils.isBlank(post.getTitle())) {
            throw new UserException("Post title is empty");
        }

        if (StringUtils.isBlank(post.getContent())) {
            throw new UserException("Post content is empty");
        }

        post.setUserId(currentUserId);

        return postDao.createPost(post);
    }

    @Override
    public void removePostById(long id, long currentUserId) throws UserException, AccessDeniedException {
        User user = userDao.getUserById(currentUserId);
        Post post = postDao.getPostById(id);
        if (post.getUserId() != currentUserId) {
            if (!user.getRights().contains(ERight.POST_ADMIN)) {
                throw new AccessDeniedException("User don't have right to delete posts");
            }
        }

        postDao.deletePostById(id);
    }
}
