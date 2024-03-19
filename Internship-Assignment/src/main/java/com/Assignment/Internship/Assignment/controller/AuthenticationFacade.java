package com.Assignment.Internship.Assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.Assignment.Internship.Assignment.service.UserService;

@Component
public class AuthenticationFacade {

    @Autowired
    private UserService userService; // Inject UserService

    public Long getUserId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        return userService.findUserIdByUsername(username);
    }
}
