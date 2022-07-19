package com.learn.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.learn.data.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {

}
