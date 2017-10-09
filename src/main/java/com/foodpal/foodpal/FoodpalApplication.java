package com.foodpal.foodpal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = "com.foodpal.foodpal")
public class FoodpalApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodpalApplication.class, args);
	}

	@Bean
	CommandLineRunner init(AccountRepository accountRepository,
						   GroceryListRepository groceryListRepository) {
		return (evt) -> Arrays.asList(
				"teddy,michael".split(","))
				.forEach(
						a -> {
							Account account = accountRepository.save(new Account(a,
									"password"));
							GroceryList list = groceryListRepository.save(new GroceryList("Grocery List", account));
						});
	}
}
