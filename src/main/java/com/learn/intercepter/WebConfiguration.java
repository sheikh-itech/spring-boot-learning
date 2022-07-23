package com.learn.intercepter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Autowired
	private ProductInterceptor interceptor;
	
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(interceptor);
	}
	
	/** This is for globally cross origin allow*/
	public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/learning").allowedMethods("*");
        //If don't want cross origin use below
        //registry.addMapping("/**").allowedMethods("*");
    }
}
