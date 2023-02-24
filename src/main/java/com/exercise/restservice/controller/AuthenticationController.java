package com.exercise.restservice.controller;

import com.exercise.restservice.common.JWTTokenHelper;
import com.exercise.restservice.entity.User;
import com.exercise.restservice.entity.model.AuthenticationRequest;
import com.exercise.restservice.entity.model.LoginResponse;
import com.exercise.restservice.entity.model.UserDto;
import com.exercise.restservice.entity.model.UserInfo;
import com.exercise.restservice.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@RequiredArgsConstructor
public class AuthenticationController {



	private final CustomUserService userService;

	private final JWTTokenHelper jWTTokenHelper;

	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;


	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {

		final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUserName(), authenticationRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		User user = (User) authentication.getPrincipal();
		String jwtToken = jWTTokenHelper.generateToken(user.getUsername(),  user.getAuthorities());

		LoginResponse response = new LoginResponse();
		response.setToken(jwtToken);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/auth/create")
	public ResponseEntity<?> create(@RequestBody UserDto userDto) {
		userService.createUser(userDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/auth/userinfo")
	public ResponseEntity<?> getUserInfo(Principal user) {

		UserInfo userInfo = userService.getUser(user);
		return ResponseEntity.ok(userInfo);


	}
}
