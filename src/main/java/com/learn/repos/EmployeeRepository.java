package com.learn.repos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.learn.data.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

	List<Employee> findByFirstName(String firstName);
	List<Employee> findByAddress(String address);
	List<Employee> findByState(String state);
	List<Employee> findByAge(int age);
	List<Employee> findByQualification(String qualification);
	List<Employee> findByDesignation(String designation);
	List<Employee> findByLastName(String lastName);
	List<Employee> findByCity(String city);
}
