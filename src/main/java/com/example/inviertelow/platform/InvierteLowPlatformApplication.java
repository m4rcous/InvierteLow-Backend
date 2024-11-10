package com.example.inviertelow.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class InvierteLowPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvierteLowPlatformApplication.class, args);
	}

}
