package com.vueart.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VueArtApplication {

	public static void main(String[] args) {
		SpringApplication.run(VueArtApplication.class, args);
	}

}
