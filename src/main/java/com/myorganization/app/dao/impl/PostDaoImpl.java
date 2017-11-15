package com.myorganization.app.dao.impl;

import com.myorganization.app.dao.PostDao;
import com.myorganization.app.model.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostDaoImpl implements PostDao {

    private static final Map<Long, Post> posts = new HashMap<>();

    @Override
    public long createPost(Post post) {
        long id = System.currentTimeMillis();
        post.setId(id);

        posts.put(id, post);

        return id;
    }

    @Override
    public void deletePostById(long id) {
        posts.remove(id);
    }

    @Override
    public Post getPostById(long id) {
        return posts.get(id);
    }

    @Override
    public List<Post> getAllPosts() {
        return new ArrayList<>(posts.values());
    }

    @Override
    public void deleteAllPosts() {
        posts.clear();
    }

}
