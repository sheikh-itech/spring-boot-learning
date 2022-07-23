package com.learn.rest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learn.data.Student;
import com.learn.repos.StudentRepository;

@RestController
@RequestMapping(value="/student")
public class StudentControllerDemoOnly {

	private static final Logger logger = LoggerFactory.getLogger(StudentControllerDemoOnly.class);
	
	@Autowired
	private Student s1;
	@Autowired
	private Student s2;
	@Autowired
	StudentRepository repository;
	
	@RequestMapping(value = "/dummy", method = RequestMethod.GET)
	public void getProducts(HttpSession session) {
		ServletContext context = session.getServletContext();
		//Scope is prototype -> so s1 & s2 are two different objects
		System.out.println("s1 == s2: "+(s1 == s2));
		System.out.println("s1.hashCode: "+s1.hashCode());
		System.out.println("s2.hashCode: "+s2.hashCode());
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Student saveStudent() {
		
		s1.setId(12);
		s1.setName("hapheej");
		s1.setMobile(9753219502l);
		s1.getAddress().setAddress("Narsinghpur");
		
		repository.save(s1);
		logger.info("Student info saved");
		return s1;
	}
}
