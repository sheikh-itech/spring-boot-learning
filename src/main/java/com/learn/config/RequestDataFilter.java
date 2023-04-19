package com.learn.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.data.Product;

@Component
public class RequestDataFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		if(request.getMethod().equalsIgnoreCase("OPTIONS")) {
    		
        	response.addHeader("Access-Control-Allow-Origin", "*");
        	response.addHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,PATCH,OPTIONS");
        	response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, Access-Control-Allow-Origin, Access-Control-Allow-Headers");
        	return;
        	
        } else if(request.getRequestURI().indexOf("/filter/product/add")>0) {
        	RequestWrapper wrappedRequest = new RequestWrapper((HttpServletRequest) request);
			
			ObjectMapper mapper = new ObjectMapper();
			String body = wrappedRequest.getBody();
			
			Product product = mapper.readValue(body, Product.class);
			System.out.println(product);
			product.setName(product.getName()+", Modified");
			wrappedRequest.setBody(mapper.writeValueAsString(product));
			chain.doFilter(wrappedRequest, response);
    		return;
    	}
		chain.doFilter(request, response);
	}
}
