package com.rest.service;


import com.rest.entities.User;
import com.rest.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    // 🔥 CREATE USER
    public User createUser(User user) {

        user.setPassword(encoder.encode(user.getPassword()));

        if (repo.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("User with this name already exists");
        }
        return repo.save(user);
    }

    // 🔥 LOGIN
    public User login(String username, String password) {

        Optional<User> userOpt = repo.findByUsername(username);

        if (userOpt.isPresent()) {

            User user = userOpt.get();

            if (encoder.matches(password, user.getPassword())) {

                user.setLastLogin(LocalDateTime.now());
                repo.save(user);

                return user;
            }
        }

        return null;
    }

    // 🔥 GET ALL USERS
    public java.util.List<User> getAllUsers() {
        return repo.findAll();
    }

    // 🔥 ENABLE / DISABLE
    public User toggleStatus(Long id, boolean status) {

        User user = repo.findById(id).orElse(null);

        if (user != null) {
            user.setEnabled(status);
            return repo.save(user);
        }

        return null;
    }

    public void enableUser(Long id) {

        User user = repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setEnabled(true);

        repo.save(user);
    }

    public void disableUser(Long id) {

        User user = repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setEnabled(false);

        repo.save(user);
    }


    public String changePassword(String username, String oldPassword, String newPassword) {

        User user = repo.findByUsername(username).orElse(null);

        if (user == null) {
            return "User not found";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // check old password
        if (!encoder.matches(oldPassword, user.getPassword())) {
            return "Old password is incorrect";
        }

        // update new password
        user.setPassword(encoder.encode(newPassword));
        repo.save(user);

        return "Password changed successfully";
    }

}
