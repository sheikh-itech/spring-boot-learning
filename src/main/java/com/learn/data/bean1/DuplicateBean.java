package com.learn.data.bean1;

import org.springframework.stereotype.Component;

@Component(value="DuplicateBean1")
public class DuplicateBean {

	private String firstBean;

	public DuplicateBean() {	
		this.firstBean = "Arham";
	}
	
	public DuplicateBean(String firstBean) {
		super();
		this.firstBean = firstBean;
	}

	public String getFirstBean() {
		return firstBean;
	}

	public void setFirstBean(String firstBean) {
		this.firstBean = firstBean;
	}

	@Override
	public String toString() {
		return "{firstBean: " + firstBean + "}";
	}
}
