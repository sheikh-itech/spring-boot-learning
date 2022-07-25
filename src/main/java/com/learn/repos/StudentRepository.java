package com.learn.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.learn.data.Student;

@Repository
//public interface StudentRepository extends MongoRepository<Student, Integer>{
public interface StudentRepository extends PagingAndSortingRepository<Student, String> {

}
