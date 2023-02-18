package com.exercise.restservice.service;

import com.exercise.restservice.entity.Posts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    //
    private final PostsProcessor postsProcessor;

    public List<Posts> getAll() {

        List<Posts> posts = postsProcessor.getAllPosts();
        return posts;
    }

    public Posts getById(Long id) {

        Posts post = postsProcessor.getPostById(id);
        return post;

    }

    public Posts savePosts(Posts post) {

        Posts post1 = postsProcessor.save(post);
        return post1;
    }


    public Posts updatePosts(Posts post) {

        Posts updatedPost = postsProcessor.update(post);
        return updatedPost;
    }

    public Posts deletePosts(Long id) {

        Posts post = postsProcessor.delete(id);
        return post;

    }
}
