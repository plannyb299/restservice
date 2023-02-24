package com.exercise.restservice.service;

import com.exercise.restservice.entity.PostDb;
import com.exercise.restservice.entity.Posts;
import com.exercise.restservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostsProcessor {

    @Value("${posts.url}")
    private String postsUrl;

    private final PostRepository postRepository;
    private final RestTemplate restTemplate;

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 5000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }
    public Set<Posts> getAllPosts(){
       List<PostDb> mypost= postRepository.findAll();
        Set<Posts> myposts = new HashSet<>();
        for(PostDb post: mypost) {
            Posts pp =new Posts();
            BeanUtils.copyProperties(post, pp);
            myposts.add(pp);
        }


        return myposts;
    }
    public void createPost(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Posts[]> posts = restTemplate.exchange(postsUrl, HttpMethod.GET, entity, Posts[].class);
        List<Posts> posts1 = Arrays.asList(posts.getBody());
        for(Posts post: posts.getBody()) {
            PostDb postDb =new PostDb();
            BeanUtils.copyProperties(post, postDb);
            postRepository.save(postDb);
        }



    }

    public Posts getPostById(Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Posts> post = restTemplate.exchange(postsUrl+id, HttpMethod.GET, entity, Posts.class);

        log.info("Post", post.getBody());
        return post.getBody();
    }

    public Posts save(Posts post){
        HttpEntity<Posts> request = new HttpEntity<>(new Posts());
        Posts posts = restTemplate.postForObject(postsUrl, request, Posts.class);

        return posts;
    }

    public Posts update(Posts post){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Posts> entity = new HttpEntity<Posts>(post,headers);

        Posts updatedPost = restTemplate.exchange(
                postsUrl+post.getId(), HttpMethod.PUT, entity, Posts.class).getBody();
        return updatedPost;
    }

    public Posts delete(Long id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(postsUrl+id, HttpMethod.DELETE,entity, Posts.class).getBody();

    }
}
