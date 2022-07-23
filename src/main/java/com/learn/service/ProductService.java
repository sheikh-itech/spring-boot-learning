package com.learn.service;

import java.util.Map;

import com.learn.data.Product;

public interface ProductService {

	Map<Integer, Product> list();
	void update(Product product);
	void add(Product product) throws Exception;
	void delete(int id);
}
