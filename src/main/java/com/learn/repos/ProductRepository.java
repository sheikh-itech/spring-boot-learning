package com.learn.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.learn.data.Product;

@Component
public interface ProductRepository extends MongoRepository<Product, Integer> {

}
