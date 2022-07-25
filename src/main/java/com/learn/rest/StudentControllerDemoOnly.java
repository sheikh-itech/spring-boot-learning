package com.learn.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learn.data.Address;
import com.learn.data.Landmark;
import com.learn.data.Student;
import com.learn.repos.AddressRepository;
import com.learn.repos.StudentRepository;
import com.learn.sequence.NextSequence;

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
	@Autowired
	AddressRepository address;
	@Autowired
	private NextSequence sequence;
	
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
		
		s1.setId(sequence.getNextSequence());
		s1.setName("hapheej");
		s1.setMobile(9753219502l);
		Landmark mark = new Landmark();
		mark.setDetail("Near Agrawal hospital");
		Address adr = new Address();
		adr.setAddress("Narsinghpur");
		adr.setId(s1.getId());
		adr.setLandmark(mark);
		s1.setAddress(adr);
		List<String> names = new ArrayList<String>();
		names.add("sheikh");
		names.add("hapheej");
		s1.setNames(names);
		//address.save(s1.getAddress());
		repository.save(s1);
		logger.info("Student info saved");
		return s1;
	}
}
