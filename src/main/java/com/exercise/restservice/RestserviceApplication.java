package com.exercise.restservice;

import com.exercise.restservice.entity.Authority;
import com.exercise.restservice.entity.User;
import com.exercise.restservice.repository.UserDetailsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@RequiredArgsConstructor
public class RestserviceApplication {

//	@Autowired
//	private PasswordEncoder passwordEncoder;


	private final UserDetailsRepository userDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestserviceApplication.class, args);
	}

	@Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

//	@PostConstruct
//	protected void init() {
//
//		List<Authority> authorityList=new ArrayList<>();
//
//		authorityList.add(createAuthority("USER","User role"));
//		authorityList.add(createAuthority("ADMIN","Admin role"));
//
//		User user=new User();
//
//		user.setUserName("pardeep161");
//		user.setFirstName("Pardeep");
//		user.setLastName("K");
//
//		user.setPassword(passwordEncoder.encode("pardeep@123"));
//		user.setEnabled(true);
//		user.setAuthorities(authorityList);
//
//		userDetailsRepository.save(user);
//	}
//
//	private Authority createAuthority(String roleCode,String roleDescription) {
//		Authority authority=new Authority();
//		authority.setRoleCode(roleCode);
//		authority.setRoleDescription(roleDescription);
//		return authority;
//	}

}
