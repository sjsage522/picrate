package com.junseok.picrate;

import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableJpaAuditing
@SpringBootApplication
public class PicrateApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicrateApplication.class, args);
	}

}
