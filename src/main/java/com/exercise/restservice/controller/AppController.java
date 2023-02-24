package com.exercise.restservice.controller;

import com.exercise.restservice.entity.Posts;
import com.exercise.restservice.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("posts/")
@Slf4j
public class AppController {

	private final PostService postService;
	@GetMapping("/create")
	public 	ResponseEntity<?>  createPosts (){
		postService.createPosts();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/all")
	public Set<Posts> getPostList() {

		return postService.getAll();
	}

	@GetMapping("getById/{id}")
	public Posts getPostById(@PathVariable("id") Long id) {
		return postService.getById(id);
	}

	@PostMapping("/save")
	public ResponseEntity savePost(@RequestBody Posts posts) {
		postService.savePosts(posts);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity updatePost(@PathVariable("id") Long id, @RequestBody Posts posts) {
		Posts posts1 = postService.updatePosts(posts);
		return ResponseEntity.ok(posts1);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity deletePost(@PathVariable("id") Long id) {
		postService.deletePosts(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
