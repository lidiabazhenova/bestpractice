package com.myorganization.app.dao.impl;

import com.myorganization.app.dao.PostDao;
import com.myorganization.app.model.Post;

import java.util.HashMap;
import java.util.Map;

public class PostDaoImpl implements PostDao {

    private static final Map<Long, Post> posts = new HashMap<>();

    public PostDaoImpl() {
        final Post post1 = new Post(1, "post 1", "content1", 1);
        posts.put(post1.getId(), post1);

        final Post post2 = new Post(2, "post 2", "content2", 2);
        posts.put(post2.getId(), post2);
    }

    public long createPost(Post post) {
        post.setId(System.currentTimeMillis());
        posts.put(post.getId(), post);

        return post.getId();
    }

    public void deletePostById(long id) {
        posts.remove(id);
    }

    public Post getPostById(long id) {
        return posts.get(id);
    }
}
