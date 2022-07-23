package com.learn.data;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//No serializer found for class com.learn.data.Product --> Getters&Setters
//Optional collection name
@Document(collection="product")
public class Product {
	
	@Id
	private int id;
	//Custom ValidationConfig will handle it
	@NotNull(message = "Product Name Should Not be Null")
	private String name;
	private float price;
	
	public Product() {
		
	}
	
	public Product(int id, String name, float price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "{id: " + id + ", name: " + name + ", price: " + price + "}";
	}
}
