package com.learn.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ProductFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(ProductFilter.class);
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
			throws IOException, ServletException {
		
		/*Reader reader = request.getReader();
		BufferedReader br = new BufferedReader(reader);
		String s1 = br.readLine();
		String s2 = br.readLine();*/
		
		logger.info("Servlet Filter");
		//Causes the next filter in the chain to be invoked,
		//or if the calling filter is the last filter in the chain,
		//causes the resource at the end of the chain to be invoked
		filterchain.doFilter(request, response);
	}
	
	public void init(FilterConfig filterconfig) throws ServletException {
		
	}
	
	public void destroy() {
		
	}
}
