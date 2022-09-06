package com.learn.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.data.Employee;
import com.learn.repos.EmployeeRepository;

@RestController
@RequestMapping("employee")
//@GetMapping	The annotation @GetMapping is disallowed for this location
public class EmployeeController {

	@Autowired
	private EmployeeRepository repository;
	
	@RequestMapping(value="/listAll")
	//@GetMapping
	public ResponseEntity<List<Employee>> getEmployees() {
		System.out.println("Listing all Employees");
		return new ResponseEntity<List<Employee>>(repository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveEmployee(@RequestBody Employee e) {
		repository.save(e);
		return "saved employee detail";
	}
	
	@RequestMapping(value="/firstName", method=RequestMethod.GET)
	public List<Employee> getEmployeeByFirstName(@RequestParam("firstName") String firstName) {

		try {
			
			return repository.findByFirstName(firstName);
		} catch(Exception ex) {
			System.out.println(ex);
		}
		return new ArrayList<Employee>();
	}
	
	@RequestMapping(value="/lastName")
	public List<Employee> getEmployeeByLastName(@RequestParam("lastName") String lastName) {
		
		return repository.findByLastName(lastName);
	}
	
	@RequestMapping(value="/designation")
	public List<Employee> getEmployeeByDesignation(@RequestParam("designation") String designation) {
		
		return repository.findByDesignation(designation);
	}
	
	@RequestMapping(value="/qualification")
	public List<Employee> getEmployeeByQualification(@RequestParam("qualification") String qualification) {
		
		return repository.findByQualification(qualification);
	}
	
	@RequestMapping(value="/age")
	public List<Employee> getEmployeeByAge(@RequestParam("age") int age) {
		
		return repository.findByAge(age);
	}
	
	@RequestMapping(value="/city")
	public List<Employee> getEmployeeByCity(@RequestParam("city") String city) {
		
		return repository.findByCity(city);
	}
	
	@RequestMapping(value="/state")
	public List<Employee> getEmployeeByState(@RequestParam("state") String state) {
		
		return repository.findByState(state);
	}
	
	@RequestMapping(value="/address")
	public List<Employee> getEmployeeByAddress(@RequestParam("address") String address) {
		
		return repository.findByAddress(address);
	}	
}
