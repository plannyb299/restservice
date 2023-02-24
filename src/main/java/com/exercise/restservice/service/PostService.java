package com.exercise.restservice.service;

import com.exercise.restservice.entity.PostDb;
import com.exercise.restservice.entity.Posts;
import com.exercise.restservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    //
    private final PostsProcessor postsProcessor;

    public Set<Posts> getAll() {

        Set<Posts> posts = postsProcessor.getAllPosts();
        return posts;
    }
    public  void createPosts()
    {
        postsProcessor.createPost();
    }
    public Posts getById(Long id) {
        Posts post = new Posts();
        PostDb postDb = postRepository.findById(id).get();
        BeanUtils.copyProperties(postDb,post);
        return post;

    }

    public Posts savePosts(Posts post) {

        Posts post1 = postsProcessor.save(post);
        return post1;
    }


    public Posts updatePosts(Posts post) {
          PostDb foundPost = postRepository.findById(post.getId()).get();

          if(foundPost!=null){
              BeanUtils.copyProperties(post,foundPost);
              postRepository.save(foundPost);
          }

        return post;
    }

    public void deletePosts(Long id) {

        postRepository.deleteById(id);


    }
}
