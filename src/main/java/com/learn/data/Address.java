package com.learn.data;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component("address")
public class Address {

	@Id
	private int id;
	private String address;
	
	public Address() {
		
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
