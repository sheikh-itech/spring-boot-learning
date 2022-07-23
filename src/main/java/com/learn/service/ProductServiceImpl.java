package com.learn.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.learn.data.Data;
import com.learn.data.Product;
import com.learn.excp.DuplicateProductException;
import com.learn.excp.ProductNotFoundException;
import com.learn.excp.validation.CustomFieldsException;
import com.learn.excp.validation.ProductDuplicacyException;
import com.learn.repos.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	private Map<Integer, Product> products;
	
	@Autowired
	private ProductRepository repository;
	@Value("${savetomongodb}")
	private boolean saveToMongoDB;
	
	{
		products =new HashMap<Integer, Product>();
		products.put(101, Data.getProduct(101, "Honey", 75.0f));
		products.put(102, Data.getProduct(102, "Ground Nuts", 140.0f));
		products.put(103, Data.getProduct(103, "Chilli Powder", 275.0f));		
	}
	
	public Map<Integer, Product> list() {
		
		logger.info(products.size()+" products served");
		return products;
	}
	
	public void update(Product product) {
		
		if(products.containsKey(product.getId())) {
			Product p = products.put(product.getId(), product);
			if(null!=p) {
				logger.info("Product Updated: "+p);
				return;
			}
			logger.info("Not able to update product: "+product);
			throw new ProductNotFoundException();
		}
		
		Optional<Product> p = repository.findById(product.getId());
		if(p.isPresent()) {
			Product pp = p.get();
			pp.setName(product.getName());
			pp.setPrice(product.getPrice());
			repository.save(pp);
		}
		
		logger.info("Not able to update product: "+product);
		throw new ProductNotFoundException();
	}
	
	public void add(Product product) throws Exception {
		
		/*if(products.containsKey(product.getId()))
			throw new DuplicateProductException();*/
		
		products.put(product.getId(), product);
		
		logger.info("Product Added: "+product.getId());
		
		if(saveToMongoDB) {
			try {
				Optional<Product> result = repository.findById(product.getId());
				if(!result.isPresent())
					repository.save(product);
				else
					throw new ProductDuplicacyException("Product is duplicate, save other one");
			} catch(Exception ex) {
				logger.info(ex.toString());
				throw ex;
			}
		}
	}
	
	public void delete(int id) {
		
		Product product = products.remove(id);
		if(null == product)
			throw new ProductNotFoundException();
		
		logger.info("Product deleted: "+product.toString());
	}
}
