package com.learn.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LearningTech {

	@Id
	private int id;
	private Course courses;
	private Address address;
	private Faculty faculty;
	
	public LearningTech() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Course getCourses() {
		return courses;
	}

	public void setCourses(Course courses) {
		this.courses = courses;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
}
