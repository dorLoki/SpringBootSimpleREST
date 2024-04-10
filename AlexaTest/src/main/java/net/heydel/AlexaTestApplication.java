package net.heydel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import net.heydel.security.InitDB;

@SpringBootApplication
public class AlexaTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlexaTestApplication.class, args);
	}

	@Bean
	CommandLineRunner init(InitDB initDB) {
		return args -> {
			initDB.init();
		};
	}
}
