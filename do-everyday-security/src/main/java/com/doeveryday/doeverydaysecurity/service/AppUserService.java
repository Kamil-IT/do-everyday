package com.doeveryday.doeverydaysecurity.service;

import com.doeveryday.doeverydaysecurity.model.AppUser;
import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface AppUserService {

    AppUser saveUser(AppUser user);

    List<AppUser> getUsers();

    AppUser findById(UUID id) throws NotFoundException;

    void deleteById(UUID id) throws NotFoundException;

    void updateUser(AppUser user) throws NotFoundException;

    boolean existsById(UUID id);

    AppUser findByUsername(String username) throws NotFoundException;

    byte [] addImage(UUID id, MultipartFile file) throws NotFoundException;

    boolean UsernameExist(String username);

    AppUser updateUsername(UUID id, String username) throws NotFoundException;
}
