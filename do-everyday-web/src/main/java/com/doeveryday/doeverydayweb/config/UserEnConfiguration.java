package com.doeveryday.doeverydayweb.config;

import com.doeveryday.doeverydayweb.model.Language;
import com.doeveryday.doeverydayweb.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("En-en")
public class UserEnConfiguration {

    @Bean
    User user(){
        User user = new User();
        user.setLanguage(Language.en);
        return user;
    }
}
