package com.learn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@ComponentScan(basePackages = { "com.genpact" })
//@EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class })

public class SpringBootLearningApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootLearningApplication.class);
	
	@Autowired
	RestTemplate restTemplate;
	@Value("${spring.application.name}")
	private String appName;

	public static void main(String[] args) {

		SpringApplication.run(SpringBootLearningApplication.class, args);
		logger.info("Spring Application Ready to Serve");
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();   
	}
}