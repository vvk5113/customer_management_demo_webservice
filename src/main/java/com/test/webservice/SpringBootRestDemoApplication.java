package com.test.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.test"})
public class SpringBootRestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestDemoApplication.class, args);
	}
}
