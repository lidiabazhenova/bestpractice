package com.myorganization.app.service;

import com.myorganization.app.exception.AccessDeniedException;
import com.myorganization.app.exception.UserException;
import com.myorganization.app.model.Post;

public interface PostService {

    Post getPostById(long id, long currentUserId) throws UserException, AccessDeniedException;

    long createPost(Post post, long currentUserId) throws UserException, AccessDeniedException;

    void removePostById(long id, long currentUserId) throws UserException, AccessDeniedException;

}
