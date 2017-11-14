package com.myorganization.app.dao;

import com.myorganization.app.model.Post;

public interface PostDao {

    long createPost(Post post);

    void deletePostById(long id);

    Post getPostById(long id);

}
