package com.learn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.learn.data.Student;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableScheduling
@EnableAdminServer	//This will mark Application as Admin Server
//@ComponentScan(basePackages = { "com.genpact" })
//@EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class })
public class SpringBootLearningApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootLearningApplication.class);
	
	@Autowired
	RestTemplate restTemplate;
	@Value("${spring.application.name}")
	private String appName;

	//username-> client
	//password-> client
	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(SpringBootLearningApplication.class, args);
		logger.info("Spring Application Ready to Serve");
		Student p = (Student)context.getBean(Student.class);
		System.out.println(p.getName());
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();   
	}
	
	/**	
	 * This is to allow CORS Origin Globally
	 * Commenting this moved to WebConfiguration file
	 */
	/*@Bean
	public WebMvcConfigurer corsConfigurer() {
		
		return new WebMvcConfigurer() {
			
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/learning");
			}
		};
	}*/
}
