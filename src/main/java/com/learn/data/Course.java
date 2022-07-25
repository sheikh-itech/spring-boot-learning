package com.learn.data;

import java.util.Map;

import org.springframework.data.annotation.Id;

public class Course {

	@Id
	private int id;
	private String name;
	private Map<String, String> courseDetail;
	
	public Course() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getCourseDetail() {
		return courseDetail;
	}

	public void setCourseDetail(Map<String, String> courseDetail) {
		this.courseDetail = courseDetail;
	}
}
