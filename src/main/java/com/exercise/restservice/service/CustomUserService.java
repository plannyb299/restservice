package com.exercise.restservice.service;

import com.exercise.restservice.entity.User;
import com.exercise.restservice.entity.model.UserDto;
import com.exercise.restservice.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {


	private final UserDetailsRepository userDetailsRepository;

	private final UserProcessor userProcessor;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		User user = userDetailsRepository.findByUserName(username);

		if (null == user) {
			throw new UsernameNotFoundException("User Not Found with userName " + username);
		}
		return user;
	}

	public UserDetails createUser(UserDto userDto) {

		User user = userProcessor.createUser(userDto);
		 userDetailsRepository.save(user);

		return user;
	}


}
