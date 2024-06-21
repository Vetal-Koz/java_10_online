package org.example.final_server;

import lombok.RequiredArgsConstructor;
import org.example.final_server.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class FinalServerApplication {

	private final PasswordEncoder passwordEncoder;
	private final UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(FinalServerApplication.class, args);
	}

}
