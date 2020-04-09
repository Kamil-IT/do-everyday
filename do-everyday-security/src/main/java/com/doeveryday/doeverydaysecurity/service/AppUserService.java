package com.doeveryday.doeverydaysecurity.service;

import com.doeveryday.doeverydaysecurity.repository.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        log.error(bCryptPasswordEncoder.encode("password"));
        log.error(bCryptPasswordEncoder.encode("password"));
        log.error(bCryptPasswordEncoder.encode("password"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null){
            throw new UsernameNotFoundException("Username cannot be null");
        }
        return appUserRepository.findFirstByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Not found user with name: " + username));
    }
}
