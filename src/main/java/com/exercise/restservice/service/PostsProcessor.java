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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public List<Posts> getAllPosts(){
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
        return posts1;
    }

    public Posts getPostById(PostDb postDb){

        Posts post = new Posts();
        BeanUtils.copyProperties(postDb,post);

//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<Posts> post = restTemplate.exchange(postsUrl+id, HttpMethod.GET, entity, Posts.class);
//
//        log.info("Post", post.getBody());
        return post;
    }

    public List<Posts> getPostByUserId(List<PostDb> postDb){

        List<Posts> posts = new ArrayList<Posts>();
        for(PostDb post : postDb) {
            Posts posts1 = new Posts();
            BeanUtils.copyProperties(post,posts1);
            posts.add(posts1);
        }

        return posts;
    }



    public Posts save(Posts post){
        HttpEntity<Posts> request = new HttpEntity<>(post);
        Posts posts = restTemplate.postForObject(postsUrl, request, Posts.class);

        return posts;
    }

    public Posts update(Posts post){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Posts> entity = new HttpEntity<Posts>(post,headers);

        Posts updatedPost = restTemplate.exchange(
                postsUrl+post.getId(), HttpMethod.PUT, entity, Posts.class).getBody();
        return updatedPost;
    }

//    public  delete(Long id){
//
//         postRepository.deleteById(id);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
//        return restTemplate.exchange(postsUrl+id, HttpMethod.DELETE,entity, Posts.class).getBody();
//    }
}
