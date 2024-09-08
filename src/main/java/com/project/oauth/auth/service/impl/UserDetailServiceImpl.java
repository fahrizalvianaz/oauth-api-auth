package com.project.oauth.auth.service.impl;

import com.project.oauth.auth.model.Users;
import com.project.oauth.auth.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {


    @Autowired
    UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Users> user = Optional.ofNullable(usersRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Username not found " + username
                )));
        return User.withUsername(user.get().getUsername())
                .password(user.get().getPassword())
                .roles(user.get().getRoles().toArray(String[]::new))
                .build();
    }
}
