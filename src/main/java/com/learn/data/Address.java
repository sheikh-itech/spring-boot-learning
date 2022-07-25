package com.learn.data;

import org.springframework.data.annotation.Id;

public class Address {

	@Id
	private int id;
	private String address;
	private Landmark landmark;
	
	public Address() {

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Landmark getLandmark() {
		return landmark;
	}

	public void setLandmark(Landmark landmark) {
		this.landmark = landmark;
	}
}
