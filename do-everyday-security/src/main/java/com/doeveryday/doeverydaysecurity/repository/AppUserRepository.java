package com.doeveryday.doeverydaysecurity.repository;

import com.doeveryday.doeverydaysecurity.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, String> {

    Optional<AppUser> findFirstByUsername(String username);

    Boolean existsByUsername(String username);
}
