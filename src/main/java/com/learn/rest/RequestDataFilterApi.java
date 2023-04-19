package com.learn.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learn.data.Product;
import com.learn.service.ProductService;

@RestController
@RequestMapping(value="/filter/product")
public class RequestDataFilterApi {

	@Autowired
	private ProductService service;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProduct(@RequestBody Product product) throws Exception {

		//This request modified by RequestDataFilter
		
		service.add(product);
		return "Product filtered, modified and added succesfuly";
	}
}
