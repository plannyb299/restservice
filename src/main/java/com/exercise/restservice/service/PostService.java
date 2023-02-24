package com.exercise.restservice.service;

import com.exercise.restservice.entity.PostDb;
import com.exercise.restservice.entity.Posts;
import com.exercise.restservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    //
    private final PostsProcessor postsProcessor;

    private final PostRepository postRepository;

    public List<Posts> getAll() {

        List<Posts> posts = postsProcessor.getAllPosts();
        return posts;
    }

    public Posts getById(Long id) {

        Posts post = new Posts();
        PostDb postDb = postRepository.findById(id).get();

        if(postDb!=null) {
            post = postsProcessor.getPostById(postDb);
        }
        return post;

    }

    public List<Posts> getByUserId(Long id) {

        List<Posts> post = new ArrayList<>();
        List<PostDb> postDb = postRepository.findByUserId(id);

        if(postDb!=null) {
            post = postsProcessor.getPostByUserId(postDb);
        }
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

    public void deletePosts(Long id) {
        postRepository.deleteById(id);

    }
}
