package com.doeveryday.doeverydaysecurity.service;

import com.doeveryday.doeverydaysecurity.model.AppUser;
import com.doeveryday.doeverydaysecurity.repository.AppUserRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class AppUserServiceImpl implements AppUserService {

    public static final String USERNAME_MUST_BE_UNIQUE_MESSAGE = "Username must be unique";
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser saveUser(AppUser user) {
        if (user.getUsername() == null){
            throw new NullPointerException("Username cannot be null");
        }
        if (user.getUsername().length() == 0){
            throw new NullPointerException("Username cannot be null");
        }
        if (appUserRepository.existsByUsername(user.getUsername())){
            throw new IllegalArgumentException(USERNAME_MUST_BE_UNIQUE_MESSAGE);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return appUserRepository.save(user);
    }

    @Override
    public List<AppUser> getUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser findById(String id) throws NotFoundException {
        return appUserRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found user with id: " + id));
    }

    @Override
    public void deleteById(String id) throws NotFoundException {
        if (!appUserRepository.existsById(id)){
            throw new NotFoundException("Not found user with id: " + id);
        }
        appUserRepository.deleteById(id);
    }

    @Override
    public void updateUser(AppUser user) throws NotFoundException {
        if (!appUserRepository.existsById(user.getId())){
            throw new NotFoundException("Not found user with id: " + user.getId());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        appUserRepository.save(user);
    }

    @Override
    public boolean existsById(String id) {
        return appUserRepository.existsById(id);
    }

    @Override
    public AppUser findByUsername(String username) throws NotFoundException {
        return appUserRepository.findFirstByUsername(username).orElseThrow(() ->
                new NotFoundException("Not found user with name: " + username));
    }

    @Override
    public byte[] addImage(String id, MultipartFile file) throws NotFoundException {
        AppUser user = appUserRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Not found user with id: " + id));

        try {
            byte[] fileBytes = new byte[file.getBytes().length];

            int i = 0;
            for (byte b : file.getBytes()){
                fileBytes[i++] = b;
            }

            user.setPhoto(fileBytes);

            return appUserRepository.save(user).getPhoto();
        } catch (IOException e) {
            log.error("Image cannot be convert to bytes");
            e.printStackTrace();
        }

        return new byte[0];
    }

    @Override
    public boolean UsernameExist(String username) {
        return appUserRepository.existsByUsername(username);
    }

    @Override
    public AppUser updateUsername(String id, String newUsername) throws NotFoundException {
        if (appUserRepository.existsByUsername(newUsername)){
            throw new IllegalArgumentException(USERNAME_MUST_BE_UNIQUE_MESSAGE);
        }
        AppUser user = appUserRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Not found user with id: " + id));
        user.setUsername(newUsername);
        return appUserRepository.save(user);
    }
}
