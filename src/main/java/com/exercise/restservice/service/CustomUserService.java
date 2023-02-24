package com.exercise.restservice.service;

import com.exercise.restservice.entity.User;
import com.exercise.restservice.entity.model.UserDto;
import com.exercise.restservice.entity.model.UserInfo;
import com.exercise.restservice.repository.UserDetailsRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;


@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private UserProcessor userProcessor;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDetailsRepository.findByUserName(username);

		if (null == user) {
			throw new UsernameNotFoundException("User Not Found with userName " + username);
		}
		return user;
	}

//	public LoginResponse loginUser(AuthenticationRequest authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {
//
//	}

	public UserDetails createUser(UserDto userDto) {

		User user = userProcessor.generateUser(userDto);
		userDetailsRepository.save(user);

		return user;
	}

	public UserInfo getUser(Principal user) {
		User userObj = (User) userDetailsService.loadUserByUsername(user.getName());
		UserInfo userInfo = userProcessor.userInfo(userObj);

		return userInfo;
	}

}
