package com.learn.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.learn.data.Data;
import com.learn.data.Product;

@RestController
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProduct() {
		
		List<Product> products = new ArrayList<Product>();
		products.add(Data.getProduct(101, "Honey", 75.0f));
		products.add(Data.getProduct(101, "Ground Nuts", 140.0f));
		products.add(Data.getProduct(101, "Chilli Powder", 275.0f));
		
		logger.info("Serving products");
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/productlist", method = RequestMethod.GET)
	public List<Product> getProductList() {
		
		List<Product> products = new ArrayList<Product>();
		products.add(Data.getProduct(101, "Honey", 75.0f));
		products.add(Data.getProduct(101, "Ground Nuts", 140.0f));
		products.add(Data.getProduct(101, "Chilli Powder", 275.0f));
		
		logger.info("Serving products");
		return products;
	}
}
