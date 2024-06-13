package org.example.final_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FinalServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalServerApplication.class, args);
	}

}
