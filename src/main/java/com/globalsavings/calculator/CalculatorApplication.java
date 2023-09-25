package com.globalsavings.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class of calculator application.
 */
@SpringBootApplication
public class CalculatorApplication {

	/**
     * Application entry point.
     *
     * @param args expose any command-line arguments.
     */
	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);
	}

}
