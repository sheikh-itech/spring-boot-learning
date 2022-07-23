package app.commandline.beans;

import org.springframework.stereotype.Component;

@Component(value="t1")
public class Test {

	public Test (){
		System.out.println("Test Initiated");
	}
	
	public void test() {
		System.out.println("test class object created automatically");
	}
}
