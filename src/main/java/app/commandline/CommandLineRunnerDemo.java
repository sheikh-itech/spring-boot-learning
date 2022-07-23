package app.commandline;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.learn.SpringBootLearningApplication;

import app.commandline.beans.Test;

/**
 *	It is used to execute the code after the Spring Boot application started 
 *	It calls run methods after spring boot up
 */

@SpringBootApplication
@ComponentScan(basePackages = {"app.commandline.beans"})
public class CommandLineRunnerDemo implements CommandLineRunner {


	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				SpringApplication.run(SpringBootLearningApplication.class, args);
		
		Test t = (Test) context.getBean("t1");
		t.test();
	}
	
	public void run(String... args) throws Exception {
		//Any customization/Cleanup code after application started
		System.out.println("Application started with CommandLineRunner...");
	}
}
