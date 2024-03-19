package com.Assignment.Internship.Assignment.repository;

import com.Assignment.Internship.Assignment.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Test
    void testFindByUsername() {
        // Mocking a user entity
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("testUser");
        userEntity.setPassword("testPassword");

        // Setting up the mock behavior for findByUsername method
        when(userRepository.findByUsername("testUser")).thenReturn(userEntity);

        // Calling the findByUsername method
        UserEntity foundUser = userRepository.findByUsername("testUser");

        // Asserting the results
        assertEquals("testUser", foundUser.getUsername());
        assertEquals("testPassword", foundUser.getPassword());
    }
}
