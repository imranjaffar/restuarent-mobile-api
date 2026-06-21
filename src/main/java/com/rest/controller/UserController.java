package com.rest.controller;


import com.rest.dto.ChangePasswordRequest;
import com.rest.entities.User;
import com.rest.security.JwtUtil;
import com.rest.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // 🔥 CREATE USER
    @PostMapping("/create")
    public User create(@RequestBody User user) {
        return service.createUser(user);
    }



    // 🔥 GET ALL USERS
    @GetMapping
    public List<User> getAll() {
        return service.getAllUsers();
    }

    // 🔥 ENABLE / DISABLE
    @PostMapping("/status")
    public User updateStatus(
            @RequestParam Long id,
            @RequestParam boolean enabled
    ) {
        return service.toggleStatus(id, enabled);
    }

    @PostMapping("/enable/{id}")
    public User enableUser(
            @PathVariable Long id) {

       return service.toggleStatus(id, true);

    }

    @PostMapping("/disable/{id}")
    public User disableUser(
            @PathVariable Long id) {
        return service.toggleStatus(id, false);
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestBody ChangePasswordRequest req,
                                 @RequestHeader("Authorization") String token) {
        String username = JwtUtil.extractUsername(token.substring(7));

        return service.changePassword(username, req.oldPassword, req.newPassword);
    }


}