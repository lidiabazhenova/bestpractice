package com.myorganization.app.dao;

import com.myorganization.app.model.Post;

import java.util.List;

public interface PostDao {

    long createPost(Post post);

    void deletePostById(long id);

    Post getPostById(long id);

    List<Post> getAllPosts();

    void deleteAllPosts();
}
