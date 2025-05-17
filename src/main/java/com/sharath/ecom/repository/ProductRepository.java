package com.sharath.ecom.repository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sharath.ecom.model.Product;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query("{ 'name' : ?0 }")
    List<Product> searchByName(String name);

}
