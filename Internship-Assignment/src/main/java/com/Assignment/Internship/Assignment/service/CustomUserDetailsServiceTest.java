package com.Assignment.Internship.Assignment.service;

import com.Assignment.Internship.Assignment.entity.UserEntity;
import com.Assignment.Internship.Assignment.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService userDetailsService;

    @Test
    void testLoadUserByUsername() {
        // Mocking a user entity
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("testUser");
        userEntity.setPassword("testPassword");
        userEntity.setRoles(Collections.singleton("ROLE_USER")); // Assuming the user has only one role

        // Setting up the mock behavior for userRepository.findByUsername method
        when(userRepository.findByUsername("testUser")).thenReturn(userEntity);

        // Calling the loadUserByUsername method
        UserDetails userDetails = userDetailsService.loadUserByUsername("testUser");

        // Asserting the results
        assertEquals("testUser", userDetails.getUsername());
        assertEquals("testPassword", userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
        assertEquals("ROLE_USER", userDetails.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Setting up the mock behavior for userRepository.findByUsername method to return null
        when(userRepository.findByUsername("nonExistingUser")).thenReturn(null);

        // Calling the loadUserByUsername method with a non-existing username
        // Asserting that UsernameNotFoundException is thrown
        UsernameNotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername("nonExistingUser")
        );

        // Asserting the exception message
        assertEquals("User not found", exception.getMessage());
    }
}
