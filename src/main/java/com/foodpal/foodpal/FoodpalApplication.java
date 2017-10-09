package com.foodpal.foodpal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.foodpal.foodpal")
public class FoodpalApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodpalApplication.class, args);
	}
}
