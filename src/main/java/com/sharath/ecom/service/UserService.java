package com.sharath.ecom.service;

import com.sharath.ecom.model.User;
import com.sharath.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public Optional<User> authenticateUser(String email, String rawPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) return Optional.empty();

        User user = userOpt.get();
        if (passwordEncoder.matches(rawPassword, user.getPassword())) {
            return Optional.of(user);
        }

        return Optional.empty();
    }

    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> updateUser(String id, User updates) {
        Optional<User> existingOpt = userRepository.findById(id);
        if (existingOpt.isEmpty()) return Optional.empty();

        User existing = existingOpt.get();

        if(updates.getFirstName() != null) existing.setFirstName(updates.getFirstName());
        if(updates.getLastName() != null) existing.setLastName(updates.getLastName());
        if(updates.getPhoneNumber() != null) existing.setPhoneNumber(updates.getPhoneNumber());
        if(updates.getStreet() != null) existing.setStreet(updates.getStreet());
        if(updates.getHouseNumber() != null) existing.setHouseNumber(updates.getHouseNumber());
        if(updates.getPostcode() != null) existing.setPostcode(updates.getPostcode());
        if(updates.getLocation() != null) existing.setLocation(updates.getLocation());
        if(updates.getGender() != null) existing.setGender(updates.getGender());

        return Optional.of(userRepository.save(existing));
    }
}
