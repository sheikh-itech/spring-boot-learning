package com.learn.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *	We can register navigation flow using Controller
 *	As shown in this class for index.html file  
 */

@RestController
public class WebController {

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/view-products")
	public String viewProducts() {
		
		return "view-products";
	}
	
	@RequestMapping("/add-products")
	public String addProducts() {
		
		return "add-products";   
	}
}
