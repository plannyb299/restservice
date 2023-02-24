package com.exercise.restservice.service;

import com.exercise.restservice.entity.Authority;
import com.exercise.restservice.entity.User;
import com.exercise.restservice.entity.model.UserDto;
import com.exercise.restservice.entity.model.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserProcessor {

    @Lazy
    private final PasswordEncoder passwordEncoder;


    public User generateUser(UserDto userDto) {

        List<Authority> authorityList = new ArrayList<>();

        authorityList.add(createAuthority(userDto));
//        authorityList.add(createAuthority("ADMIN", "Admin role"));

        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(true);
        user.setAuthorities(authorityList);

        return user;

    }

    public UserInfo userInfo(User userObj) {

        UserInfo userInfo = new UserInfo();
        userInfo.setFirstName(userObj.getFirstName());
        userInfo.setLastName(userObj.getLastName());
        userInfo.setRoles(userObj.getAuthorities().toArray());


        return userInfo;
    }

    private Authority createAuthority(UserDto userDto) {
        Authority authority = new Authority();
        authority.setRoleCode(userDto.getAuthority().getRoleCode());
        authority.setRoleDescription(userDto.getAuthority().getRoleDescription());
        return authority;
    }
}
