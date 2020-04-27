package com.doeveryday.doeverydaysecurity.service;

import com.doeveryday.doeverydaysecurity.model.AppUser;
import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AppUserService {

    AppUser saveUser(AppUser user);

    List<AppUser> getUsers();

    AppUser findById(String id) throws NotFoundException;

    void deleteById(String id) throws NotFoundException;

    void updateUser(AppUser user) throws NotFoundException;

    boolean existsById(String id);

    AppUser findByUsername(String username) throws NotFoundException;

    byte [] addImage(String id, MultipartFile file) throws NotFoundException;

    boolean UsernameExist(String username);

    AppUser updateUsername(String id, String username) throws NotFoundException;
}
