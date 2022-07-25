package com.learn.data;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
//@Scope(value="prototype")
//@Scope(value="singleton") by default
public class Student {

	
	@Id
	private int id;
	private String name;
	private long mobile;
	private Address address;
	
	public Student() {
		//names = new ArrayList<String>();
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

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<String> getNames() {
		return null;
	}

	public void setNames(List<String> names) {
		//this.names = names;
	}
}
