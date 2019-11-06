package com.alex.springBoot.springBootApp;

import com.alex.springBoot.springBootApp.entities.User;
import com.alex.springBoot.springBootApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication/*(exclude = SecurityAutoConfiguration.class)*/			// отключил безопасность
public class SpringBootAppApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User newAdmin = new User("admin@mail.ru", "admin", "admin");
		userService.createAdmin(newAdmin);
	}
}
