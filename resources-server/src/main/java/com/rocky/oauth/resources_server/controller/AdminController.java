package com.rocky.oauth.resources_server.controller;

import com.rocky.oauth.resources_server.domain.UserProfile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {

    @RequestMapping("/users")
    public ResponseEntity<List<UserProfile>> getAllUsers() {
        return ResponseEntity.ok(getUsers());
    }

    private List<UserProfile> getUsers() {
        List<UserProfile> users = new ArrayList<>();
        users.add(new UserProfile("adolfo", "adolfo@mailinator.com"));
        users.add(new UserProfile("demigreite", "demigreite@mailinator.com"));
        users.add(new UserProfile("jujuba", "jujuba@mailinator.com"));
        return users;
    }
}
