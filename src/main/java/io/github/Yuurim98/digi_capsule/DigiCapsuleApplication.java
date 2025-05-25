package io.github.Yuurim98.digi_capsule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DigiCapsuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigiCapsuleApplication.class, args);
	}

}
