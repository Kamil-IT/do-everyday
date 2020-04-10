package com.doeveryday.doeverydaysecurity.service;

import com.doeveryday.doeverydaysecurity.model.AppUser;
import com.doeveryday.doeverydaysecurity.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    AppUserRepository appUserRepository;

    UserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        userDetailsService = new UserDetailsServiceImpl(appUserRepository);
    }

    @Test
    void loadUserByUsername() {
        AppUser user = AppUser.builder().username("Name").password("password").build();
        UserDetails userDetails = user;

        when(appUserRepository.findFirstByUsername(anyString())).thenReturn(Optional.of(user));

        assertEquals(userDetails, userDetailsService.loadUserByUsername(anyString()));
    }

    @Test
    void loadUserByUsername_Null() {
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(null));
    }

    @Test
    void loadUserByUsername_NotFound() {
        AppUser user = AppUser.builder().username("Name").password("password").build();
        UserDetails userDetails = user;

        when(appUserRepository.findFirstByUsername(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(anyString()));
    }
}