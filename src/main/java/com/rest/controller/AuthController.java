package com.rest.controller;


import com.rest.entities.User;
import com.rest.repository.UserRepository;
import com.rest.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository repo) {
        this.repo = repo;
    }

    // 🔥 LOGIN
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User req) {

        User user = repo.findByUsername(req.getUsername())
                .orElse(null);

        Map<String, Object> res = new HashMap<>();

        if (user != null && encoder.matches(req.getPassword(), user.getPassword())) {

            String token = JwtUtil.generateToken(
                    user.getUsername(),
                    user.getRole()
            );

            res.put("token", token);
            res.put("role", user.getRole());
            res.put("username", user.getUsername());

        } else {
            res.put("error", "Invalid credentials");
        }

        return res;
    }
}