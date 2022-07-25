package com.learn.data;

import java.util.Map;

import org.springframework.data.annotation.Id;

public class Faculty {

	@Id
	private int id;
	private Map<String, String> detail;
	
	public Faculty() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, String> getDetail() {
		return detail;
	}

	public void setDetail(Map<String, String> detail) {
		this.detail = detail;
	}
}
