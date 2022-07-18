package com.learn.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learn.data.Product;
import com.learn.service.ProductService;

@RestController
@RequestMapping(value="/product")
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<Map<Integer, Product>> getProducts() {
		
		return new ResponseEntity<Map<Integer, Product>>(service.list(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.PUT)
	public String updateProduct(@RequestBody Product product) {
		
		service.update(product);
		return "Product updated";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProduct(@RequestBody Product product) {
		
		service.add(product);
		return "Product added succesfuly";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String deleteProduct(@PathVariable("id") int id) {
		
		service.delete(id);
		return "Product deleted success";
	}
}
