package net.fender.springboot.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ExampleSpringBootService {

	public static void main(String[] args) {
		SpringApplication.run(ExampleSpringBootService.class, args);
	}

}