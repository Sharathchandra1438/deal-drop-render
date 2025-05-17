package com.sharath.ecom.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sharath.ecom.model.User;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
