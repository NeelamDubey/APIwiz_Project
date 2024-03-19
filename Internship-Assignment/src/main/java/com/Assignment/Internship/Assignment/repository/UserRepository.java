package com.Assignment.Internship.Assignment.repository;

import com.Assignment.Internship.Assignment.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
