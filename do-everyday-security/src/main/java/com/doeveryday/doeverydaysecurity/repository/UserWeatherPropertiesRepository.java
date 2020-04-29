package com.doeveryday.doeverydaysecurity.repository;

import com.doeveryday.doeverydaysecurity.model.userproperties.UserWeatherProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserWeatherPropertiesRepository extends JpaRepository<UserWeatherProperties, String> {
    Optional<UserWeatherProperties> findFirstByUser_Id(String user_id);

    Optional<UserWeatherProperties> findFirstByUser_Username(String username);
}
