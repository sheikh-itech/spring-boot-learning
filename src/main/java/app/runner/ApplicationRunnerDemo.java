package app.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learn.SpringBootLearningApplication;

/**
 *	Application Runner interfaces lets us to execute the code 
 *	After the Spring Boot application is started
 *
 *	We can use this interfaces to perform any actions immediately 
 *	After the application has started
 */

//@SpringBootApplication
public class ApplicationRunnerDemo implements ApplicationRunner {
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootLearningApplication.class, args);
	}
	

	/**		ApplicationRunner run(...) method
	 * 	Application Runner is an interface used to execute the code 
	 * 	After the Spring Boot application started
	 */
	public void run(ApplicationArguments args) throws Exception {
		//Any customization/Cleanup code after application started
		System.out.println("Application started with ApplicationRunner...");		
	}
}
