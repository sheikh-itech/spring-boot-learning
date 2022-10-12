package com.learn.data.bean2;

import org.springframework.stereotype.Component;

@Component(value="DuplicateBean2")
public class DuplicateBean {

	private String secondBean;

	public DuplicateBean() {
		secondBean = "Vehleen";
	}
	
	public DuplicateBean(String secondBean) {
		super();
		this.secondBean = secondBean;
	}

	public String getSecondBean() {
		return secondBean;
	}

	public void setSecondBean(String secondBean) {
		this.secondBean = secondBean;
	}

	@Override
	public String toString() {
		return "{secondBean: " + secondBean + "}";
	}
}
