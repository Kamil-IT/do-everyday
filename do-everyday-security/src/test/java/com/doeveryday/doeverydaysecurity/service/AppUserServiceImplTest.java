package com.doeveryday.doeverydaysecurity.service;

import com.doeveryday.doeverydaysecurity.model.AppUser;
import com.doeveryday.doeverydaysecurity.repository.AppUserRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.doeveryday.doeverydaysecurity.model.AppUserRole.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppUserServiceImplTest {

    public static final String PASSWORD = "password";
    public static final String NAME = "Name";
    @Mock
    AppUserRepository appUserRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    AppUserService appUserService;

    @BeforeEach
    void setUp() {
        appUserService = new AppUserServiceImpl(appUserRepository, passwordEncoder);
    }

    @Test
    void saveUser() {
        AppUser user = AppUser.builder()
                .username(NAME)
                .password(PASSWORD)
                .role(USER).build();

        when(passwordEncoder.encode(anyString())).thenReturn("encoded_string");
        when(appUserRepository.existsByUsername(anyString())).thenReturn(false);
        when(appUserRepository.save(any(AppUser.class))).thenReturn(user);

        AppUser actual = appUserService.saveUser(user);
        assertEquals("encoded_string", actual.getPassword());
    }

    @Test
    void saveUser_UsernameNull() {
        AppUser user = AppUser.builder()
                .username(null)
                .password(PASSWORD).build();

        assertThrows(NullPointerException.class, () -> appUserService.saveUser(user));
    }

    @Test
    void saveUser_NameAvailableInDB() {
        AppUser user = AppUser.builder()
                .username(NAME)
                .password(PASSWORD)
                .role(USER).build();

        when(appUserRepository.existsByUsername(anyString())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> appUserService.saveUser(user));
    }

    @Test
    void getUsers() {
        List<AppUser> users = Arrays.asList(AppUser.builder().build(), AppUser.builder().build());

        when(appUserRepository.findAll()).thenReturn(users);

        assertEquals(2, appUserService.getUsers().size());
    }

    @Test
    void findById() throws NotFoundException {
        AppUser user = AppUser.builder()
                .username(NAME)
                .password(PASSWORD).build();

        when(appUserRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));

        assertEquals(user, appUserService.findById(UUID.randomUUID()));
    }

    @Test
    void findById_NotFound() throws NotFoundException {
        AppUser user = AppUser.builder()
                .username(NAME)
                .password(PASSWORD).build();

        when(appUserRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> appUserService.findById(UUID.randomUUID()));
    }

    @Test
    void deleteById() throws NotFoundException {
        AppUser user = AppUser.builder()
                .username(NAME)
                .password(PASSWORD).build();
        UUID id = UUID.randomUUID();

        when(appUserRepository.existsById(any(UUID.class))).thenReturn(true);

        appUserService.deleteById(id);

        verify(appUserRepository, times(1)).deleteById(any(UUID.class));
    }

    @Test
    void deleteById_NotFound() throws NotFoundException {
        AppUser user = AppUser.builder()
                .username(NAME)
                .password(PASSWORD).build();
        UUID id = UUID.randomUUID();

        when(appUserRepository.existsById(any(UUID.class))).thenReturn(false);

        assertThrows(NotFoundException.class, () -> appUserService.deleteById(id));
    }

    @Test
    void updateUser() throws NotFoundException {
        UUID id = UUID.randomUUID();
        AppUser user = AppUser.builder()
                .id(id)
                .username(NAME)
                .password(PASSWORD).build();

        when(passwordEncoder.encode(anyString())).thenReturn("encoded_string");
        when(appUserRepository.existsById(id)).thenReturn(true);
        when(appUserRepository.save(any(AppUser.class))).thenReturn(user);

        appUserService.updateUser(user);

        verify(appUserRepository, times(1)).save(user);
        verify(appUserRepository, times(1)).existsById(id);
    }

    @Test
    void updateUser_NotFoundId() throws NotFoundException {
        AppUser user = AppUser.builder()
                .id(UUID.randomUUID())
                .username(NAME)
                .password(PASSWORD).build();

        when(appUserRepository.existsById(any(UUID.class))).thenReturn(false);

        assertThrows(NotFoundException.class, () -> appUserService.updateUser(user));
    }

    @Test
    void existsById_Found() {
        UUID id = UUID.randomUUID();

        when(appUserRepository.existsById(id)).thenReturn(true);

        assertTrue(appUserService.existsById(id));
        verify(appUserRepository, times(1)).existsById(id);
    }

    @Test
    void existsById_NotFound() {
        UUID id = UUID.randomUUID();

        when(appUserRepository.existsById(id)).thenReturn(false);

        assertFalse(appUserService.existsById(id));
        verify(appUserRepository, times(1)).existsById(id);
    }

    @Test
    void findByUsername() throws NotFoundException {
        AppUser user = AppUser.builder()
                .id(UUID.randomUUID())
                .username(NAME)
                .password(PASSWORD).build();

        when(appUserRepository.findFirstByUsername(any(String.class))).thenReturn(Optional.of(user));

        assertEquals(user, appUserService.findByUsername(NAME));
    }

    @Test
    void findByUsername_NotFound() throws NotFoundException {
        AppUser user = AppUser.builder()
                .id(UUID.randomUUID())
                .username(NAME)
                .password(PASSWORD).build();

        when(appUserRepository.findFirstByUsername(any(String.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> appUserService.findByUsername(NAME));
    }

    @Test
    void addImage_NotFoundUser() {
        when(appUserRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> appUserService.addImage(UUID.randomUUID(), null));
    }
}