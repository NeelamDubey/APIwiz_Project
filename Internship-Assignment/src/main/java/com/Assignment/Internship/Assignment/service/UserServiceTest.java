package com.Assignment.Internship.Assignment.service;

import com.Assignment.Internship.Assignment.entity.UserEntity;
import com.Assignment.Internship.Assignment.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindUserIdByUsername_ExistingUser() {
        // Arrange
        String username = "testuser";
        long userId = 1L;
        UserEntity user = new UserEntity();
        user.setId(userId);
        when(userRepository.findByUsername(username)).thenReturn(user);

        // Act
        long result = userService.findUserIdByUsername(username);

        // Assert
        assertEquals(userId, result);
        verify(userRepository, times(1)).findByUsername(username);
    }

    @Test
    void testFindUserIdByUsername_NonExistingUser() {
        // Arrange
        String username = "nonexistinguser";
        when(userRepository.findByUsername(username)).thenReturn(null);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> userService.findUserIdByUsername(username));
        verify(userRepository, times(1)).findByUsername(username);
    }
}
