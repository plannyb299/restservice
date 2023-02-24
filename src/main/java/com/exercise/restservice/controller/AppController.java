package com.exercise.restservice.controller;

import com.exercise.restservice.entity.Posts;
import com.exercise.restservice.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("posts/")
@Slf4j
public class AppController {

	private final PostService postService;

	@GetMapping("")
	@RolesAllowed({"USER", "ADMIN"})
	public List<Posts> getPostList() {

		return postService.getAll();
	}

	@GetMapping("getById/{id}")
	@RolesAllowed({"USER", "ADMIN"})
	public Posts getPostById(@PathVariable("id") Long id) {
		return postService.getById(id);
	}

	@PostMapping("/save")
	@RolesAllowed({"USER", "ADMIN"})
	public ResponseEntity savePost(@RequestBody Posts posts) {
		postService.savePosts(posts);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	@RolesAllowed("ADMIN")
	public ResponseEntity updatePost(@PathVariable("userId") Long id, @RequestBody Posts posts) {
		Posts posts1 = postService.updatePosts(posts);
		return ResponseEntity.ok(posts1);
	}

	@DeleteMapping("/delete/{id}")
	@RolesAllowed("ADMIN")
	public ResponseEntity<?> deletePost(@PathVariable("id") Long id) {
		 postService.deletePosts(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
