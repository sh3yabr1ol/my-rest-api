package com.cwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class CwtRestApi1Application {

	public static void main(String[] args) {
		SpringApplication.run(CwtRestApi1Application.class, args);
	}

}
