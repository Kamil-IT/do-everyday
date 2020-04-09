package com.doeveryday.doeverydaysecurity.service;

import com.doeveryday.doeverydaysecurity.model.AppUser;
import com.doeveryday.doeverydaysecurity.repository.AppUserRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class AppUserServiceImpl implements UserDetailsService, AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null){
            throw new UsernameNotFoundException("Username cannot be null");
        }
        return appUserRepository.findFirstByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Not found user with name: " + username));
    }

    @Override
    public AppUser saveUser(AppUser user) {
        if (user.getUsername() == null){
            throw new NullPointerException("Username cannot be null");
        }
        Optional<AppUser> firstByUsername = appUserRepository.findFirstByUsername(user.getUsername());
        if (firstByUsername.get() != null){
            throw new IllegalArgumentException("Username must be unique");
        }

        return appUserRepository.save(user);
    }

    @Override
    public List<AppUser> getUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser findById(UUID id) throws NotFoundException {
        return appUserRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found user with id: " + id));
    }

    @Override
    public void deleteById(UUID id) throws NotFoundException {
        if (!appUserRepository.existsById(id)){
            throw new NotFoundException("Not found user with id: " + id);
        }
        appUserRepository.deleteById(id);
    }

    @Override
    public void updateBudget(AppUser user) throws NotFoundException {
        if (!appUserRepository.existsById(user.getId())){
            throw new NotFoundException("Not found user with id: " + user.getId());
        }
        appUserRepository.save(user);
    }

    @Override
    public boolean existsById(UUID id) {
        return appUserRepository.existsById(id);
    }

    @Override
    public AppUser findByUsername(String username) throws NotFoundException {
        return appUserRepository.findFirstByUsername(username).orElseThrow(() -> new NotFoundException("Not found user with name: " + username));
    }
}
