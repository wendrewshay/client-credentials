package com.rocky.oauth.authorization_server.controller;

import com.rocky.oauth.authorization_server.domain.UserProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/user")
    public ResponseEntity<UserProfile> profile() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = user.getUsername() + "@mailinator.com";

        UserProfile profile = new UserProfile();
        profile.setName(user.getUsername());
        profile.setEmail(email);
        return ResponseEntity.ok(profile);
    }
}
