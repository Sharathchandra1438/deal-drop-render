package com.sharath.ecom.controller;

import com.sharath.ecom.model.User;
import com.sharath.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User createdUser = userService.registerUser(user);
            createdUser.setPassword(null);
            return ResponseEntity.ok(createdUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        Optional<User> userOpt = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        User user = userOpt.get();
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        Optional<User> userOpt = userService.findUserById(id);
        if(userOpt.isEmpty()) return ResponseEntity.notFound().build();

        User user = userOpt.get();
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User updates) {
        Optional<User> updatedUserOpt = userService.updateUser(id, updates);
        if(updatedUserOpt.isEmpty()) return ResponseEntity.notFound().build();

        User updatedUser = updatedUserOpt.get();
        updatedUser.setPassword(null);
        return ResponseEntity.ok(updatedUser);
    }
}