package com.learn.data;

import java.util.Arrays;
import java.util.List;

public class Data {

	static public List<Integer> getNumber() {
		
		return Arrays.asList(23, 45, 6, 7, 89, 21, 31, 45, 2, 7, 9, 45);
	}
	
	static public List<String> getNames() {
		
		return Arrays.asList("sheikh", "hapheej", "mansoori");
	}
	
	static public Product getProduct(int id, String name, float price) {
		
		return new Product(id, name, price);
	}
}
