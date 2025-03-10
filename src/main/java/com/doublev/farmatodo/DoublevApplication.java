package com.doublev.farmatodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.doublev.farmatodo")
public class DoublevApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoublevApplication.class, args);
	}

}
