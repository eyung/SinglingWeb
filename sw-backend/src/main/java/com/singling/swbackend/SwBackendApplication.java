package com.singling.swbackend;

import com.sun.tools.javac.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SwBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> {

		};
	}
}
