package Vendingmachine;


import Vendingmachine.config.LoggingController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GroovyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroovyApplication.class, args);
		new LoggingController().index();

	}
}
