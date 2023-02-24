package com.exercise.restservice.service;

import com.exercise.restservice.entity.Authority;
import com.exercise.restservice.entity.User;
import com.exercise.restservice.entity.model.UserDto;
import com.exercise.restservice.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProcessor {

    @Autowired
    private  PasswordEncoder passwordEncoder;


    public User createUser(UserDto userDto) {

        List<Authority> authorityList=new ArrayList<>();

        authorityList.add(createAuthority("USER","User role"));
//        authorityList.add(createAuthority("ADMIN","Admin role"));

        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(true);
        user.setAuthorities(authorityList);

        return user;

    }
    private Authority createAuthority(String roleCode, String roleDescription) {
        Authority authority=new Authority();
        authority.setRoleCode(roleCode);
        authority.setRoleDescription(roleDescription);
        return authority;
    }
}
