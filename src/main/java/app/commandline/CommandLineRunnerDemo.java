package app.commandline;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learn.SpringBootLearningApplication;

/**
 *	It is used to execute the code after the Spring Boot application started 
 */

@SpringBootApplication
public class CommandLineRunnerDemo implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(SpringBootLearningApplication.class, args);
	}
	
	public void run(String... args) throws Exception {
		//Any customization/Cleanup code after application started
		System.out.println("Application started with CommandLineRunner...");
	}
}
